// Generated by view binder compiler. Do not edit!
package com.zzuh.filot_shoppings.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.zzuh.filot_shoppings.R;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;

public final class ActivityProductDetailBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final TextView deliveryPriceItem;

  @NonNull
  public final LinearLayout detailsLayout;

  @NonNull
  public final Toolbar headerToolbar;

  @NonNull
  public final TextView itemTitle;

  @NonNull
  public final Spinner productColorSpinner;

  @NonNull
  public final ImageView productImg;

  @NonNull
  public final TextView productPriceItem;

  @NonNull
  public final Spinner productSizeSpinner;

  @NonNull
  public final RecyclerView selectedListRecyclerView;

  private ActivityProductDetailBinding(@NonNull LinearLayout rootView,
      @NonNull TextView deliveryPriceItem, @NonNull LinearLayout detailsLayout,
      @NonNull Toolbar headerToolbar, @NonNull TextView itemTitle,
      @NonNull Spinner productColorSpinner, @NonNull ImageView productImg,
      @NonNull TextView productPriceItem, @NonNull Spinner productSizeSpinner,
      @NonNull RecyclerView selectedListRecyclerView) {
    this.rootView = rootView;
    this.deliveryPriceItem = deliveryPriceItem;
    this.detailsLayout = detailsLayout;
    this.headerToolbar = headerToolbar;
    this.itemTitle = itemTitle;
    this.productColorSpinner = productColorSpinner;
    this.productImg = productImg;
    this.productPriceItem = productPriceItem;
    this.productSizeSpinner = productSizeSpinner;
    this.selectedListRecyclerView = selectedListRecyclerView;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static ActivityProductDetailBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static ActivityProductDetailBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.activity_product_detail, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static ActivityProductDetailBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.delivery_price_item;
      TextView deliveryPriceItem = ViewBindings.findChildViewById(rootView, id);
      if (deliveryPriceItem == null) {
        break missingId;
      }

      id = R.id.details_layout;
      LinearLayout detailsLayout = ViewBindings.findChildViewById(rootView, id);
      if (detailsLayout == null) {
        break missingId;
      }

      id = R.id.header_toolbar;
      Toolbar headerToolbar = ViewBindings.findChildViewById(rootView, id);
      if (headerToolbar == null) {
        break missingId;
      }

      id = R.id.item_title;
      TextView itemTitle = ViewBindings.findChildViewById(rootView, id);
      if (itemTitle == null) {
        break missingId;
      }

      id = R.id.product_color_spinner;
      Spinner productColorSpinner = ViewBindings.findChildViewById(rootView, id);
      if (productColorSpinner == null) {
        break missingId;
      }

      id = R.id.product_img;
      ImageView productImg = ViewBindings.findChildViewById(rootView, id);
      if (productImg == null) {
        break missingId;
      }

      id = R.id.product_price_item;
      TextView productPriceItem = ViewBindings.findChildViewById(rootView, id);
      if (productPriceItem == null) {
        break missingId;
      }

      id = R.id.product_size_spinner;
      Spinner productSizeSpinner = ViewBindings.findChildViewById(rootView, id);
      if (productSizeSpinner == null) {
        break missingId;
      }

      id = R.id.selected_list_recycler_view;
      RecyclerView selectedListRecyclerView = ViewBindings.findChildViewById(rootView, id);
      if (selectedListRecyclerView == null) {
        break missingId;
      }

      return new ActivityProductDetailBinding((LinearLayout) rootView, deliveryPriceItem,
          detailsLayout, headerToolbar, itemTitle, productColorSpinner, productImg,
          productPriceItem, productSizeSpinner, selectedListRecyclerView);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
