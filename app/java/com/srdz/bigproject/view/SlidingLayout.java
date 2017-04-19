package com.srdz.bigproject.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.VelocityTracker;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.widget.RelativeLayout;

public class SlidingLayout extends RelativeLayout implements OnTouchListener {

	/**
	 * ������ʾ��������಼��ʱ����ָ������Ҫ�ﵽ���ٶȡ�
	 */
	public static final int SNAP_VELOCITY = 200;

	/**
	 * ��Ļ���ֵ��
	 */
	private int screenWidth;

	/**
	 * �Ҳ಼�������Ի����������Ե��
	 */
	private int leftEdge = 0;

	/**
	 * �Ҳ಼�������Ի��������ұ�Ե��
	 */
	private int rightEdge = 0;

	/**
	 * �ڱ��ж�Ϊ����֮ǰ�û���ָ�����ƶ������ֵ��
	 */
	private int touchSlop;

	/**
	 * ��¼��ָ����ʱ�ĺ����ꡣ
	 */
	private float xDown;

	/**
	 * ��¼��ָ����ʱ�������ꡣ
	 */
	private float yDown;

	/**
	 * ��¼��ָ�ƶ�ʱ�ĺ����ꡣ
	 */
	private float xMove;

	/**
	 * ��¼��ָ�ƶ�ʱ�������ꡣ
	 */
	private float yMove;

	/**
	 * ��¼�ֻ�̧��ʱ�ĺ����ꡣ
	 */
	private float xUp;

	/**
	 * ��಼�ֵ�ǰ����ʾ�������ء�ֻ����ȫ��ʾ������ʱ�Ż���Ĵ�ֵ�����������д�ֵ��Ч��
	 */
	private boolean isLeftLayoutVisible;

	/**
	 * �Ƿ����ڻ�����
	 */
	private boolean isSliding;

	/**
	 * ��಼�ֶ���
	 */
	private View leftLayout;

	/**
	 * �Ҳ಼�ֶ���
	 */
	private View rightLayout;

	/**
	 * ���ڼ����໬�¼���View��
	 */
	private View mBindView;

	/**
	 * ��಼�ֵĲ�����ͨ���˲���������ȷ����಼�ֵĿ�ȣ��Լ�����leftMargin��ֵ��
	 */
	private MarginLayoutParams leftLayoutParams;

	/**
	 * �Ҳ಼�ֵĲ�����ͨ���˲���������ȷ���Ҳ಼�ֵĿ�ȡ�
	 */
	private MarginLayoutParams rightLayoutParams;

	/**
	 * ���ڼ�����ָ�������ٶȡ�
	 */
	private VelocityTracker mVelocityTracker;

	/**
	 * ��дSlidingLayout�Ĺ��캯�������л�ȡ����Ļ�Ŀ�ȡ�
	 * 
	 * @param context
	 * @param attrs
	 */
	@SuppressWarnings("deprecation")
	public SlidingLayout(Context context, AttributeSet attrs) {
		super(context, attrs);
		WindowManager wm = (WindowManager) context
				.getSystemService(Context.WINDOW_SERVICE);
		screenWidth = wm.getDefaultDisplay().getWidth();
		touchSlop = ViewConfiguration.get(context).getScaledTouchSlop();
	}

	/**
	 * �󶨼����໬�¼���View�����ڰ󶨵�View���л����ſ�����ʾ��������಼�֡�
	 * 
	 * @param bindView
	 *            ��Ҫ�󶨵�View����
	 */
	public void setScrollEvent(View bindView) {
		mBindView = bindView;
		mBindView.setOnTouchListener(this);
	}

	/**
	 * ����Ļ��������಼�ֽ��棬�����ٶ��趨Ϊ30.
	 */
	public void scrollToLeftLayout() {
		new ScrollTask().execute(-30);
	}

	/**
	 * ����Ļ�������Ҳ಼�ֽ��棬�����ٶ��趨Ϊ-30.
	 */
	public void scrollToRightLayout() {
		new ScrollTask().execute(30);
	}

	/**
	 * ��಼���Ƿ���ȫ��ʾ����������ȫ���أ����������д�ֵ��Ч��
	 * 
	 * @return ��಼����ȫ��ʾ����true����ȫ���ط���false��
	 */
	public boolean isLeftLayoutVisible() {
		return isLeftLayoutVisible;
	}

	/**
	 * ��onLayout�������趨��಼�ֺ��Ҳ಼�ֵĲ�����
	 */
	@Override
	protected void onLayout(boolean changed, int l, int t, int r, int b) {
		super.onLayout(changed, l, t, r, b);
		if (changed) {
			// ��ȡ��಼�ֶ���
			leftLayout = getChildAt(0);
			leftLayoutParams = (MarginLayoutParams) leftLayout
					.getLayoutParams();
			rightEdge = -leftLayoutParams.width;
			// ��ȡ�Ҳ಼�ֶ���
			rightLayout = getChildAt(1);
			rightLayoutParams = (MarginLayoutParams) rightLayout
					.getLayoutParams();
			rightLayoutParams.width = screenWidth;
			rightLayout.setLayoutParams(rightLayoutParams);
		}
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		createVelocityTracker(event);
		if (leftLayout.getVisibility() != View.VISIBLE) {
			leftLayout.setVisibility(View.VISIBLE);
		}
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			// ��ָ����ʱ����¼����ʱ�ĺ�����
			xDown = event.getRawX();
			yDown = event.getRawY();
			break;
		case MotionEvent.ACTION_MOVE:
			// ��ָ�ƶ�ʱ���ԱȰ���ʱ�ĺ����꣬������ƶ��ľ��룬�������Ҳ಼�ֵ�leftMarginֵ���Ӷ���ʾ��������಼��
			xMove = event.getRawX();
			yMove = event.getRawY();
			int moveDistanceX = (int) (xMove - xDown);
			int distanceY = (int) (yMove - yDown);
			if (!isLeftLayoutVisible && moveDistanceX >= touchSlop
					&& (isSliding || Math.abs(distanceY) <= touchSlop)) {
				isSliding = true;
				rightLayoutParams.rightMargin = -moveDistanceX;
				if (rightLayoutParams.rightMargin > leftEdge) {
					rightLayoutParams.rightMargin = leftEdge;
				}
				rightLayout.setLayoutParams(rightLayoutParams);
			}
			if (isLeftLayoutVisible && -moveDistanceX >= touchSlop) {
				isSliding = true;
				rightLayoutParams.rightMargin = rightEdge - moveDistanceX;
				if (rightLayoutParams.rightMargin < rightEdge) {
					rightLayoutParams.rightMargin = rightEdge;
				}
				rightLayout.setLayoutParams(rightLayoutParams);
			}
			break;
		case MotionEvent.ACTION_UP:
			xUp = event.getRawX();
			int upDistanceX = (int) (xUp - xDown);
			if (isSliding) {
				// ��ָ̧��ʱ�������жϵ�ǰ���Ƶ���ͼ���Ӷ������ǹ�������಼�֣����ǹ������Ҳ಼��
				if (wantToShowLeftLayout()) {
					if (shouldScrollToLeftLayout()) {
						scrollToLeftLayout();
					} else {
						scrollToRightLayout();
					}
				} else if (wantToShowRightLayout()) {
					if (shouldScrollToRightLayout()) {
						scrollToRightLayout();
					} else {
						scrollToLeftLayout();
					}
				}
			} else if (upDistanceX < touchSlop && isLeftLayoutVisible) {
				scrollToRightLayout();
			}
			recycleVelocityTracker();
			break;
		}
		if (v.isEnabled()) {
			if (isSliding) {
				unFocusBindView();
				return true;
			}
			if (isLeftLayoutVisible) {
				return true;
			}
			return false;
		}
		return true;
	}

	/**
	 * �жϵ�ǰ���Ƶ���ͼ�ǲ�������ʾ�Ҳ಼�֡������ָ�ƶ��ľ����Ǹ������ҵ�ǰ��಼���ǿɼ��ģ�����Ϊ��ǰ��������Ҫ��ʾ�Ҳ಼�֡�
	 * 
	 * @return ��ǰ��������ʾ�Ҳ಼�ַ���true�����򷵻�false��
	 */
	private boolean wantToShowRightLayout() {
		return xUp - xDown < 0 && isLeftLayoutVisible;
	}

	/**
	 * �жϵ�ǰ���Ƶ���ͼ�ǲ�������ʾ��಼�֡������ָ�ƶ��ľ������������ҵ�ǰ��಼���ǲ��ɼ��ģ�����Ϊ��ǰ��������Ҫ��ʾ��಼�֡�
	 * 
	 * @return ��ǰ��������ʾ��಼�ַ���true�����򷵻�false��
	 */
	private boolean wantToShowLeftLayout() {
		return xUp - xDown > 0 && !isLeftLayoutVisible;
	}

	/**
	 * �ж��Ƿ�Ӧ�ù�������಼��չʾ�����������ָ�ƶ����������Ļ��1/2��������ָ�ƶ��ٶȴ���SNAP_VELOCITY��
	 * ����ΪӦ�ù�������಼��չʾ������
	 * 
	 * @return ���Ӧ�ù�������಼��չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToLeftLayout() {
		return xUp - xDown > leftLayoutParams.width / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * �ж��Ƿ�Ӧ�ù������Ҳ಼��չʾ�����������ָ�ƶ��������leftLayoutPadding������Ļ��1/2��
	 * ������ָ�ƶ��ٶȴ���SNAP_VELOCITY�� ����ΪӦ�ù������Ҳ಼��չʾ������
	 * 
	 * @return ���Ӧ�ù������Ҳ಼��չʾ��������true�����򷵻�false��
	 */
	private boolean shouldScrollToRightLayout() {
		return xDown - xUp > leftLayoutParams.width / 2
				|| getScrollVelocity() > SNAP_VELOCITY;
	}

	/**
	 * ����VelocityTracker���󣬲��������¼����뵽VelocityTracker���С�
	 * 
	 * @param event
	 *            �Ҳ಼�ּ����ؼ��Ļ����¼�
	 */
	private void createVelocityTracker(MotionEvent event) {
		if (mVelocityTracker == null) {
			mVelocityTracker = VelocityTracker.obtain();
		}
		mVelocityTracker.addMovement(event);
	}

	/**
	 * ��ȡ��ָ���Ҳ಼�ֵļ���View�ϵĻ����ٶȡ�
	 * 
	 * @return �����ٶȣ���ÿ�����ƶ��˶�������ֵΪ��λ��
	 */
	private int getScrollVelocity() {
		mVelocityTracker.computeCurrentVelocity(1000);
		int velocity = (int) mVelocityTracker.getXVelocity();
		return Math.abs(velocity);
	}

	/**
	 * ����VelocityTracker����
	 */
	private void recycleVelocityTracker() {
		mVelocityTracker.recycle();
		mVelocityTracker = null;
	}

	/**
	 * ʹ�ÿ��Ի�ý���Ŀؼ��ڻ�����ʱ��ʧȥ���㡣
	 */
	private void unFocusBindView() {
		if (mBindView != null) {
			mBindView.setPressed(false);
			mBindView.setFocusable(false);
			mBindView.setFocusableInTouchMode(false);
		}
	}

	class ScrollTask extends AsyncTask<Integer, Integer, Integer> {

		@Override
		protected Integer doInBackground(Integer... speed) {
			int rightMargin = rightLayoutParams.rightMargin;
			// ���ݴ�����ٶ����������棬������������߽���ұ߽�ʱ������ѭ����
			while (true) {
				rightMargin = rightMargin + speed[0];
				if (rightMargin < rightEdge) {
					rightMargin = rightEdge;
					break;
				}
				if (rightMargin > leftEdge) {
					rightMargin = leftEdge;
					break;
				}
				publishProgress(rightMargin);
				// Ϊ��Ҫ�й���Ч��������ÿ��ѭ��ʹ�߳�˯��20���룬�������۲��ܹ���������������
				sleep(15);
			}
			if (speed[0] > 0) {
				isLeftLayoutVisible = false;
			} else {
				isLeftLayoutVisible = true;
			}
			isSliding = false;
			return rightMargin;
		}

		@Override
		protected void onProgressUpdate(Integer... rightMargin) {
			rightLayoutParams.rightMargin = rightMargin[0];
			rightLayout.setLayoutParams(rightLayoutParams);
			unFocusBindView();
		}

		@Override
		protected void onPostExecute(Integer rightMargin) {
			rightLayoutParams.rightMargin = rightMargin;
			rightLayout.setLayoutParams(rightLayoutParams);
		}
	}

	/**
	 * ʹ��ǰ�߳�˯��ָ���ĺ�������
	 * 
	 * @param millis
	 *            ָ����ǰ�߳�˯�߶�ã��Ժ���Ϊ��λ
	 */
	private void sleep(long millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}