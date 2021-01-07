package eg.edu.iss.team8androidca;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.widget.GridLayout;

public class MemoryButton extends androidx.appcompat.widget.AppCompatButton {

    protected int row;
    protected int column;
    protected Bitmap frontImage;

    protected boolean isFlipped = false;
    protected boolean isMatched = false;

    protected Drawable front;
    protected Drawable back;

    public MemoryButton(Context context, int r, int c, Bitmap frontImage) {
        super(context);
        row = r;
        column = c;
        this.frontImage = frontImage;

        Bitmap scaled = Bitmap.createScaledBitmap(frontImage, (int)(frontImage.getWidth()*0.5), (int)(frontImage.getHeight()*0.5), true);
        BitmapDrawable bdrawable = new BitmapDrawable(context.getResources(),scaled);

        front = bdrawable;
        back = context.getDrawable(R.drawable.bigquestion);

        setBackground(back);

        GridLayout.LayoutParams tempParams = new GridLayout.LayoutParams(GridLayout.spec(r), GridLayout.spec(c));

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            tempParams.width = Resources.getSystem().getDisplayMetrics().widthPixels / 3;
            tempParams.height = Resources.getSystem().getDisplayMetrics().heightPixels / 6;
        }
        else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            tempParams.width = Resources.getSystem().getDisplayMetrics().widthPixels / 6;
            tempParams.height = Resources.getSystem().getDisplayMetrics().heightPixels / 3;
        }
        tempParams.setMargins(3,3,3,3);
        setLayoutParams(tempParams);
    }

    public boolean isMatched() {
        return isMatched;
    }

    public void setMatched(boolean matched) {
        isMatched = matched;
    }

    public Bitmap getFrontImage() {
        return frontImage;
    }

    public void flip() {
        if (isMatched) {
            return;
        }
        if (isFlipped) {
            setBackground(back);
            isFlipped = false;
        } else {
            setBackground(front);
            isFlipped = true;
        }
    }
}

