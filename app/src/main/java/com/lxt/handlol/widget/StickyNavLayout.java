package com.lxt.handlol.widget;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.OverScroller;

import com.lxt.handlol.R;
import com.lxt.handlol.utils.DisplayUtil;
import com.lxt.handlol.utils.LogUtil;


public class StickyNavLayout extends LinearLayout {

	private View mTop;
	private View mNav;
	private ViewPager mViewPager;

	private int mTopViewHeight;
	private ViewGroup mInnerScrollView;
	private boolean isTopHidden = false;

	private OverScroller mScroller;
	private VelocityTracker mVelocityTracker;
	private int mTouchSlop;
	private int mMaximumVelocity, mMinimumVelocity;

	private float mLastY;
	private boolean mDragging;

	private boolean isInControl = false;
	private boolean showholeTop=true;
	public StickyNavLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(LinearLayout.VERTICAL);

		mScroller = new OverScroller(context);
		//在被判定为滚动之前用户手指可以移动的最大值。
		//这是一个距离只有当手指移动大于这个距离的时候才算是滑动
		mTouchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
		//滑动的最快速度
		mMaximumVelocity = ViewConfiguration.get(context)
				.getScaledMaximumFlingVelocity();
		//滑动的最慢速度
		mMinimumVelocity = ViewConfiguration.get(context)
				.getScaledMinimumFlingVelocity();

	}

	/**
	 * 系统解析完View之后才会调用onFinishInflate方法
	 * 我们可以获取制定子view的组件
	 */
	@Override
	protected void onFinishInflate() {
		super.onFinishInflate();
		//分别获取head,tablayout,viewpager
		mTop = findViewById(R.id.id_stickynavlayout_topview);
		mNav = findViewById(R.id.id_stickynavlayout_indicator);
		View view = findViewById(R.id.id_stickynavlayout_viewpager);
		//如果他不是viewpager报出异常
		if (!(view instanceof ViewPager)) {
			throw new RuntimeException(
					"id_stickynavlayout_viewpager show used by ViewPager !");
		}
		mViewPager = (ViewPager) view;
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		ViewGroup.LayoutParams params = mViewPager.getLayoutParams();
		params.height = getMeasuredHeight() - mNav.getMeasuredHeight();

	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		super.onSizeChanged(w, h, oldw, oldh);
		//head的高度
		mTopViewHeight = mTop.getMeasuredHeight()- DisplayUtil.dp2px(getContext(),65);
	}

	///事件分发
	@Override
	public boolean dispatchTouchEvent(MotionEvent ev) {
		int action = ev.getAction();
		float y = ev.getY();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mLastY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				float dy = y - mLastY;
				getCurrentScrollView();
				 if (mInnerScrollView instanceof RecyclerView) {
					RecyclerView lv = (RecyclerView) mInnerScrollView;
					 if(lv==null){
						 break;
					 }
					LinearLayoutManager layoutManager = (LinearLayoutManager) lv.getLayoutManager();
					 if(layoutManager==null){
						 break;
					 }
					int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
					View c=lv.getChildAt(firstVisibleItemPosition);
					if (!isInControl && c != null && c.getTop() == 0 && isTopHidden
							&& dy > 0) {
						isInControl = true;
						ev.setAction(MotionEvent.ACTION_CANCEL);
						MotionEvent ev2 = MotionEvent.obtain(ev);
						dispatchTouchEvent(ev);
						ev2.setAction(MotionEvent.ACTION_DOWN);
						return dispatchTouchEvent(ev2);
					}
				}
				break;
		}
		return super.dispatchTouchEvent(ev);
	}


	public  View supplyInfo(){
		if (mInnerScrollView instanceof RecyclerView) {
			RecyclerView lv = (RecyclerView) mInnerScrollView;
			LinearLayoutManager layoutManager = (LinearLayoutManager) lv.getLayoutManager();
			int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
			View c = lv.getChildAt(firstVisibleItemPosition);
			return c;
		}
		return null;
	}

	/**事件拦截
	 *
	 */
	@Override
	public boolean onInterceptTouchEvent(MotionEvent ev) {
		final int action = ev.getAction();
		float y = ev.getY();
		switch (action) {
			case MotionEvent.ACTION_DOWN:
				mLastY = y;
				break;
			case MotionEvent.ACTION_MOVE:
				float dy = y - mLastY;
				getCurrentScrollView();
				//如果滑动的距离大于可以判定为滑动的时候
				if (Math.abs(dy) > mTouchSlop) {
					mDragging = true;
					if (mInnerScrollView instanceof RecyclerView) {
						RecyclerView lv = (RecyclerView) mInnerScrollView;
						LinearLayoutManager layoutManager = (LinearLayoutManager) lv.getLayoutManager();
						if(layoutManager==null){
							break;
						}
						int firstVisibleItemPosition = layoutManager.findFirstVisibleItemPosition();
						View c=lv.getChildAt(firstVisibleItemPosition);
						/**
                         * 两种情况
						 * 1：top没有隐藏目的是要将top隐藏起来，滑动的是外面的view
						 * 2:viewpager中的recyclerview的item的第一个item可见，继续向下滑就是要显示出top所以移动的也是外面的view
						 */
						getParent().requestDisallowInterceptTouchEvent(true);
						if (!isTopHidden || //
								(c != null //
										&& c.getTop() == 0//
										&& isTopHidden && dy > 0)) {
							initVelocityTrackerIfNotExists();
							mVelocityTracker.addMovement(ev);
							mLastY = y;
							return true;
						}
					}

				}
				break;
			case MotionEvent.ACTION_CANCEL:
			case MotionEvent.ACTION_UP:
				mDragging = false;
				recycleVelocityTracker();
				break;
		}
		return super.onInterceptTouchEvent(ev);
	}

	private void getCurrentScrollView() {
		//获取viewpager的currentitem的fragment
		/**
		 * 获取一个fragment通过fragment来获取
		 */
		int currentItem = mViewPager.getCurrentItem();
		PagerAdapter a = mViewPager.getAdapter();
		if (a instanceof FragmentPagerAdapter) {
			FragmentPagerAdapter fadapter = (FragmentPagerAdapter) a;
			Fragment item = (Fragment) fadapter.instantiateItem(mViewPager,
					currentItem);
			//Get the root view for the fragment's layout 获取他的父亲布局
			//通过父布局来获得其中的滑动布局
			mInnerScrollView = (ViewGroup) (item.getView()
					.findViewById(R.id.id_stickynavlayout_innerscrollview));
		} else if (a instanceof FragmentStatePagerAdapter) {
			FragmentStatePagerAdapter fsAdapter = (FragmentStatePagerAdapter) a;
			Fragment item = (Fragment) fsAdapter.instantiateItem(mViewPager,
					currentItem);
			mInnerScrollView = (ViewGroup) (item.getView()
					.findViewById(R.id.id_stickynavlayout_innerscrollview));
		}

	}
	@Override
	public void scrollTo(int x, int y) {
		LogUtil.e("这是scrollTo中的y"+y);
		if (y < 0) {
			y = 0;
		}
		if (y > mTopViewHeight) {
			y = mTopViewHeight;
		}
		if (y != getScrollY()) {
			super.scrollTo(x, y);
		}
		isTopHidden = getScrollY()== mTopViewHeight;

	}


	@Override
	public boolean onTouchEvent(MotionEvent event) {
		initVelocityTrackerIfNotExists();
		mVelocityTracker.addMovement(event);
		int action = event.getAction();
		float y = event.getY();

		switch (action) {
			case MotionEvent.ACTION_DOWN:
				if (!mScroller.isFinished())
					mScroller.abortAnimation();
				mLastY = y;
				return true;
			case MotionEvent.ACTION_MOVE:
				float dy = y - mLastY;
				if (!mDragging && Math.abs(dy) > mTouchSlop) {
					mDragging = true;
				}
				if (mDragging) {
					scrollBy(0, (int) -dy);

					// 如果topView隐藏，且上滑动时，则改变当前事件为ACTION_DOWN
					if (getScrollY() == mTopViewHeight && dy < 0) {
						event.setAction(MotionEvent.ACTION_DOWN);
						dispatchTouchEvent(event);
						isInControl = false;
					}
				}

				mLastY = y;
				break;
			case MotionEvent.ACTION_CANCEL:
				mDragging = false;
				recycleVelocityTracker();
				if (!mScroller.isFinished()) {
					mScroller.abortAnimation();
				}
				break;
			case MotionEvent.ACTION_UP:
				mDragging = false;
				mVelocityTracker.computeCurrentVelocity(1000, mMaximumVelocity);
				int velocityY = (int) mVelocityTracker.getYVelocity();
				if (Math.abs(velocityY) > mMinimumVelocity) {
					fling(-velocityY);
				}
				recycleVelocityTracker();
				break;
		}

		return super.onTouchEvent(event);
	}

	public void fling(int velocityY) {
		mScroller.fling(0, getScrollY(), 0, velocityY, 0, 0, 0, mTopViewHeight);
		invalidate();
	}


	@Override
	public void computeScroll() {
		if (mScroller.computeScrollOffset()) {
			scrollTo(0, mScroller.getCurrY());
			invalidate();
		}
	}

	private void initVelocityTrackerIfNotExists() {
		if (mVelocityTracker == null) {
			//实时计算出当前的速度
			mVelocityTracker = VelocityTracker.obtain();
		}
	}

	private void recycleVelocityTracker() {
		if (mVelocityTracker != null) {
			mVelocityTracker.recycle();
			mVelocityTracker = null;
		}
	}

}
