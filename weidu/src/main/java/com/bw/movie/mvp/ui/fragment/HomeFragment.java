package com.bw.movie.mvp.ui.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bw.movie.R;
import com.bw.movie.bean.HotNews;
import com.bw.movie.bean.LookingNews;
import com.bw.movie.bean.WillNews;
import com.bw.movie.di.component.DaggerHomeComponent;
import com.bw.movie.di.module.HomeModule;
import com.bw.movie.mvp.contract.HomeContract;
import com.bw.movie.mvp.presenter.HomePresenter;
import com.bw.movie.mvp.ui.activity.HomeItemActivity;
import com.bw.movie.mvp.ui.adapter.HotAdapter;
import com.bw.movie.mvp.ui.adapter.LookingAdapter;
import com.bw.movie.mvp.ui.adapter.WillAdapter;
import com.bw.movie.mvp.ui.cengdieAdapter;
import com.jess.arms.base.BaseFragment;
import com.jess.arms.di.component.AppComponent;
import com.jess.arms.utils.ArmsUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import recycler.coverflow.RecyclerCoverFlow;

import static com.jess.arms.utils.Preconditions.checkNotNull;


public class HomeFragment extends BaseFragment<HomePresenter> implements HomeContract.View {

    Unbinder unbinder;
    @BindView(R.id.hot_recyView)
    RecyclerView hotRecyView;
    @BindView(R.id.looking_recyView)
    RecyclerView lookingRecyView;
    @BindView(R.id.will_recyView)
    RecyclerView willRecyView;
    @BindView(R.id.text_hot)
    TextView textHot;
    @BindView(R.id.text_looking)
    LinearLayout textLooking;
    @BindView(R.id.text_will)
    TextView textWill;
    @BindView(R.id.recyFlow)
    RecyclerCoverFlow recyFlow;

    public static HomeFragment newInstance() {
        HomeFragment fragment = new HomeFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(@NonNull AppComponent appComponent) {
        DaggerHomeComponent //如找不到该类,请编译一下项目
                .builder()
                .appComponent(appComponent)
                .homeModule(new HomeModule(this))
                .build()
                .inject(this);
    }

    @Override
    public View initView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void initData(@Nullable Bundle savedInstanceState) {
        mPresenter.hot();
        mPresenter.looking();
        mPresenter.will();
    }

    /**
     * 通过此方法可以使 Fragment 能够与外界做一些交互和通信, 比如说外部的 Activity 想让自己持有的某个 Fragment 对象执行一些方法,
     * 建议在有多个需要与外界交互的方法时, 统一传 {@link Message}, 通过 what 字段来区分不同的方法, 在 {@link #setData(Object)}
     * 方法中就可以 {@code switch} 做不同的操作, 这样就可以用统一的入口方法做多个不同的操作, 可以起到分发的作用
     * <p>
     * 调用此方法时请注意调用时 Fragment 的生命周期, 如果调用 {@link #setData(Object)} 方法时 {@link Fragment#onCreate(Bundle)} 还没执行
     * 但在 {@link #setData(Object)} 里却调用了 Presenter 的方法, 是会报空的, 因为 Dagger 注入是在 {@link Fragment#onCreate(Bundle)} 方法中执行的
     * 然后才创建的 Presenter, 如果要做一些初始化操作,可以不必让外部调用 {@link #setData(Object)}, 在 {@link #initData(Bundle)} 中初始化就可以了
     * <p>
     * Example usage:
     * <pre>
     * public void setData(@Nullable Object data) {
     *     if (data != null && data instanceof Message) {
     *         switch (((Message) data).what) {
     *             case 0:
     *                 loadData(((Message) data).arg1);
     *                 break;
     *             case 1:
     *                 refreshUI();
     *                 break;
     *             default:
     *                 //do something
     *                 break;
     *         }
     *     }
     * }
     *
     * // call setData(Object):
     * Message data = new Message();
     * data.what = 0;
     * data.arg1 = 1;
     * fragment.setData(data);
     * </pre>
     *
     * @param data 当不需要参数时 {@code data} 可以为 {@code null}
     */
    @Override
    public void setData(@Nullable Object data) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        ArmsUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        ArmsUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void data(HotNews hotNews) {
        ArrayList<String> list = new ArrayList<>();
        List<HotNews.ResultBean> result = hotNews.getResult();

        //热门列表
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        hotRecyView.setLayoutManager(manager);
        HotAdapter adapter1 = new HotAdapter(getActivity(), result);
        hotRecyView.setAdapter(adapter1);


       //层叠
        for (HotNews.ResultBean resultBean : result) {
            String imageUrl = resultBean.getImageUrl();
            list.add(imageUrl);
        }
        cengdieAdapter adapter = new cengdieAdapter(getActivity(),result);
        recyFlow.setAdapter(adapter);



       /* String imageUrl = result.get(0).getImageUrl();
        showUrlBlur(imageUrl);*/

     /*   //3D画廊
        HualangAdapter adapter = new HualangAdapter(result, getActivity());
        homeViewpage.setAdapter(adapter);
        homeViewpage.setPageMargin(20);
        homeViewpage.setOffscreenPageLimit(result.size());
        homeViewpage.setPageTransformer(true, new ZooOutPageTransformer());
        //左右都有图片
        homeViewpage.setCurrentItem(2);

        //高斯
        String imageUrl = result.get(0).getImageUrl();
        showUrlBlur(imageUrl);
*/
     /*   homeViewpage.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                String imageUrl1 = result.get(position).getImageUrl();
                showUrlBlur(imageUrl1);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
*/
        // initListDataAndAction();
    }


    //正在上映
    @Override
    public void datalooking(LookingNews lookingNews) {
        List<LookingNews.ResultBean> result = lookingNews.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        lookingRecyView.setLayoutManager(manager);
        LookingAdapter lookadapter = new LookingAdapter(getActivity(), result);
        lookingRecyView.setAdapter(lookadapter);
    }

    //即将上映
    @Override
    public void datawill(WillNews willNews) {
        List<WillNews.ResultBean> result = willNews.getResult();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false);
        willRecyView.setLayoutManager(manager);
        WillAdapter willAdapter = new WillAdapter(getActivity(), result);
        willRecyView.setAdapter(willAdapter);
    }


/*    //高斯模糊
    private void showUrlBlur(String imageUrl) {
        try {
            Uri uri = Uri.parse(imageUrl);
            ImageRequest request = ImageRequestBuilder.newBuilderWithSource(uri)
                    .setPostprocessor(new IterativeBoxBlurPostProcessor(2, 30))
                    .build();
            AbstractDraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setOldController(homeSimp.getController())
                    .setImageRequest(request)
                    .build();

            homeSimp.setController(controller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.text_hot, R.id.text_looking, R.id.text_will})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.text_hot:
                Intent intent = new Intent(getActivity(), HomeItemActivity.class);
                startActivity(intent);
                break;
            case R.id.text_looking:
                Intent intent1 = new Intent(getActivity(), HomeItemActivity.class);
                startActivity(intent1);
                break;
            case R.id.text_will:
                Intent intent2 = new Intent(getActivity(), HomeItemActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
