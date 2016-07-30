package com.rnrapps.user.dtuguide.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;

import com.rnrapps.user.dtuguide.R;


/**
 * Created by rohanpc on 2/27/16.
 */
public class TextView extends android.widget.TextView {
	public enum Font {
		ROBOTO_LIGHT("fonts/Roboto-Light.ttf"),
		ROBOTO_MEDIUM("fonts/Roboto-Medium.ttf"),
		ROBOTO_BOLD("fonts/Roboto-Bold.ttf"),
		ROBOTO_REGULAR("fonts/Roboto-Regular.ttf"),
		ROBOTO_CONDENSED_BOLD("fonts/RobotoCondensed-Bold.ttf"),
		ROBOTO_CONDENSED_LIGHT("fonts/RobotoCondensed-Light.ttf"),
		ROBOTO_CONDENSED_REGULAR("fonts/RobotoCondensed-Regular.ttf"),
		ROBOTO_BLACK("fonts/Roboto-Black.ttf"),
		ROBOTO_THIN("fonts/Roboto-Thin.ttf");

		private String fontPath;
		private Typeface typeface;

		Font(String fontPath) {
			this.fontPath = fontPath;
		}

		public void init(Context context) {
			typeface = Typeface.createFromAsset(context.getAssets(), fontPath);
		}

		public Typeface getTypeface() {
			return typeface;
		}
	}

	public TextView(Context context, AttributeSet attrs) {
		super(context, attrs);
		if (!isInEditMode()) {
			for (Font font : Font.values()) {
				font.init(context);
			}
			init(context, attrs);
		}
	}

	public TextView(Context context) {
		super(context);
		if (!isInEditMode()) {
			for (Font font : Font.values()) {
				font.init(context);
			}
			init(context, null);
		}
	}

	private void init(Context context, AttributeSet attrs) {
		setTypeface(Font.ROBOTO_REGULAR.getTypeface());
		setTextColor(Color.rgb(31,30,30));
		if (attrs != null) {
			TypedArray a = context.getTheme().obtainStyledAttributes(attrs, R.styleable.TextView, 0, 0);
			try {
				String value = a.getString(R.styleable.TextView_typeface);
				if (value != null) {
					setTypeface(Font.valueOf(value).getTypeface());
				}
			}
			finally {
				a.recycle();
			}

			a = context.getTheme().obtainStyledAttributes(attrs, new int[] { android.R.attr.textColor }, 0, 0);
			try {
				setTextColor(a.getColor(0, Color.rgb(31,30,30)));
			}
			finally {
				a.recycle();
			}
		}
	}
}
