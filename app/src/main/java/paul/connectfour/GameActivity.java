package paul.connectfour;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class GameActivity extends AppCompatActivity implements View.OnClickListener {

    private LinearLayout[] columns = new LinearLayout[7];
    private Game game;
    private int gameMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        this.game = new Game();
        gameMode = getIntent().getExtras().getInt("player");


        LinearLayout toolbar = (LinearLayout) findViewById(R.id.game_toolbar);
        for (int i = 0; i < 7; i++) {
            Button button = new Button(this);
            LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);

            button.setText("" + (i + 1));
            button.setLayoutParams(llp);
            button.setOnClickListener(this);
            button.setTag(i);

            toolbar.addView(button);
        }

        LinearLayout columnsLayout = (LinearLayout) findViewById(R.id.game_columns);

        for (int j = 0; j < columnsLayout.getChildCount(); j++) {
            this.columns[j] = (LinearLayout) columnsLayout.getChildAt(j);
        }


    }

    public ImageView createImageView(int player) {
        LinearLayout.LayoutParams llp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);

        ImageView iv = new ImageView(this);
        iv.setLayoutParams(llp);

        if (player == Game.RED) {
            iv.setImageResource(R.drawable.red);
        } else {
            iv.setImageResource(R.drawable.yellow);
        }

        return iv;
    }

    @Override
    public void onClick(View v) {
        int columnIndex = Integer.parseInt(v.getTag().toString());
        if (game.move(columnIndex)) {
            this.columns[columnIndex].addView(createImageView(game.getCurrentPlayer()), 0);

            if(this.checkGameOver(columnIndex)) {
                return;
            }
            game.increaseGameCounter();

            if (this.gameMode == 1) {
                int computerCol = game.computerMove();
                this.columns[computerCol].addView(createImageView(game.getCurrentPlayer()), 0);

                if(this.checkGameOver(computerCol)) {
                    return;
                }
                game.increaseGameCounter();
            }
        }
    }


    private boolean checkGameOver(int columnIndex) {
        int gameOver = this.game.checkWinner(columnIndex);

        if(gameOver != 0) {
            Intent i = new Intent(this, GameOverActivity.class);
            i.putExtra("gameState", gameOver);
            startActivity(i);
            return true;
        }
        return false;
    }
}
