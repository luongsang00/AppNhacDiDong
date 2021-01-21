package com.example.appnghenhac;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class Activity_bai_hat extends AppCompatActivity {

    TextView txt_tencasi, txt_tenbaihatdangphat, txt_tgdangphat, txt_tgketthuc;
    ImageButton IMG_play, IMG_next, IMG_re;
    ListView listView_baihat;
    SeekBar seekBar;

    int vitri;
    ArrayList<list_baihat>  list_baihats;

    MediaPlayer player = new MediaPlayer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bai_hat);

        Intent intent = getIntent();
        int vt = intent.getIntExtra("position", 0);
        String cs = intent.getStringExtra("tencasi" );


        txt_tencasi =(TextView) findViewById(R.id.txt_casi);
        txt_tenbaihatdangphat = (TextView) findViewById(R.id.txt_dang_phat);
        txt_tgdangphat = (TextView)findViewById(R.id.txt_time_play);
        txt_tgketthuc = (TextView)findViewById(R.id.txt_time_out);
        IMG_next = (ImageButton) findViewById(R.id.bt_next);
        IMG_play = (ImageButton) findViewById(R.id.bt_pause);
        IMG_re = (ImageButton) findViewById(R.id.bt_re);

        listView_baihat = (ListView) findViewById(R.id.list_nhac);
        seekBar = (SeekBar) findViewById(R.id.seebar);

        txt_tencasi.setText("Ca sĩ: " + cs);

        list_baihats = new ArrayList<list_baihat>();


        switch (vt)
        {
            case 0:
                list_baihats.add(new list_baihat("Deep Sea", time(R.raw.binz_deepsea), R.raw.binz_deepsea));
                list_baihats.add(new list_baihat("SoFar", time(R.raw.binz_sofar), R.raw.binz_sofar));
                list_baihats.add(new list_baihat("Yêu", time(R.raw.binz_yeu), R.raw.binz_yeu));
                break;
            case 1:
                list_baihats.add(new list_baihat("Điều anh biết (remix)", time(R.raw.chidan_dieuanhbiet), R.raw.chidan_dieuanhbiet));
                list_baihats.add(new list_baihat("Không quan tâm", time(R.raw.chidan_khongquantam), R.raw.chidan_khongquantam));
                list_baihats.add(new list_baihat("Người tôi yêu", time(R.raw.chidan_nguoitoiyeu), R.raw.chidan_nguoitoiyeu));
                break;
            case 2:
                list_baihats.add(new list_baihat("Stay With Me (Yêu OST)", time(R.raw.chipu_staywithme), R.raw.chipu_staywithme));
                list_baihats.add(new list_baihat("Từ hôm nay", time(R.raw.chipu_tuhomnay), R.raw.chipu_tuhomnay));
                list_baihats.add(new list_baihat("Lovew Is Hurt", time(R.raw.chipu_loveishurt),R.raw.chipu_loveishurt));
                break;
            case 3:
                list_baihats.add(new list_baihat("Anh đếch cần gì nhiều ngoài em", time(R.raw.den_anhdechcanninhieungoaiem), R.raw.den_anhdechcanninhieungoaiem));
                list_baihats.add(new list_baihat("Bài này chill phết", time(R.raw.den_bainaychillphet), R.raw.den_bainaychillphet));
                list_baihats.add(new list_baihat("Hai triệu năm", time(R.raw.den_haitrieunam), R.raw.den_haitrieunam));
                list_baihats.add(new list_baihat("Lối nhỏ", time(R.raw.den_loinho1), R.raw.den_loinho1));
                break;
            case 4:
                list_baihats.add(new list_baihat("Anh yêu người khác rồi", time(R.raw.khacviet_anhyeunguoikhacroi), R.raw.khacviet_anhyeunguoikhacroi));
                list_baihats.add(new list_baihat("Không phải anh đúng không", time(R.raw.khacviet_khongphaianhdungkhong), R.raw.khacviet_khongphaianhdungkhong));
                list_baihats.add(new list_baihat("Tội cho cô gái đó", time(R.raw.khacviet_toichocogaido), R.raw.khacviet_toichocogaido));
                list_baihats.add(new list_baihat("Yêu lại từ đầu", time(R.raw.khacviet_yeulaitudau), R.raw.khacviet_yeulaitudau));
                break;
            case 5:
                list_baihats.add(new list_baihat("Buông đôi tay nhau ra", time(R.raw.sontung_buongdoitaynhaura), R.raw.sontung_buongdoitaynhaura));
                list_baihats.add(new list_baihat("Không phải dạng vừa đâu", time(R.raw.sontung_khongphaidangvuadau), R.raw.sontung_khongphaidangvuadau));
                //list_baihats.add(new list_baihat("Khuôn mặt đáng thương", time(R.raw.k), R.raw.KhuonMatDangThuongRemix));
                break;
        }
        Adapter_listbaihat adapter_listbaihat = new Adapter_listbaihat(Activity_bai_hat.this, R.layout.list_baihat, list_baihats);
        listView_baihat.setAdapter(adapter_listbaihat);
        khoitao();
        player.start();
        IMG_play.setImageResource(R.drawable.pause);
        thoigian_hientai();

        listView_baihat.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if(player.isPlaying())
                {
                    player.stop();
                }
                else {
                    vitri = position;
                    khoitao();
                }
                IMG_play.setBackgroundResource(R.drawable.pause);
            }
        });
        IMG_play.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(player.isPlaying()) {
                    player.pause();
                    IMG_play.setImageResource(R.drawable.dung);
                }
                else {
                    player.start();
                    IMG_play.setImageResource(R.drawable.pause);
                }
            }
        });

        IMG_next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri++;
                if(vitri > list_baihats.size()-1)
                {
                    vitri = 0;
                }
                player.stop();
                khoitao();
            }
        });
        IMG_re.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                vitri--;
                if(vitri<0)
                {
                    vitri = list_baihats.size()-1;
                }
                player.stop();
                khoitao();
            }
        });
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                player.seekTo(seekBar.getProgress());
            }
        });
    }
    void thoigian_hientai()
    {
        Handler handler = new Handler();
        boolean b = handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("mm:ss");
                txt_tgdangphat.setText(simpleDateFormat.format(player.getCurrentPosition()));
                seekBar.setProgress(player.getCurrentPosition());
                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                    @Override
                    public void onCompletion(MediaPlayer mp) {
                        vitri++;
                        if(vitri > list_baihats.size()-1)
                        {
                            vitri = 0;
                            player.stop();
                            IMG_play.setImageResource(R.drawable.dung);
                        }
                        else
                        {
                            player.stop();
                            khoitao();
                            player.start();
                        }
                    }
                });
                handler.postDelayed(this, 500);
            }
        }, 100);
    }
    private void khoitao()
    {
        player = MediaPlayer.create(Activity_bai_hat.this, list_baihats.get(vitri).baihat);
        txt_tenbaihatdangphat.setText("Đang phát: "+list_baihats.get(vitri).tenbaihat);
        txt_tgketthuc.setText(time(list_baihats.get(vitri).baihat));
        seekBar.setMax(player.getDuration());
        player.start();
    }
    private String time(int baihat)
    {
        String t;
        MediaPlayer player = new MediaPlayer();
        player = MediaPlayer.create(Activity_bai_hat.this, baihat);
        SimpleDateFormat tg = new SimpleDateFormat("mm:ss");
        t = tg.format(player.getDuration());
        return t;
    }

    @Override
    protected void onStop() {
        player.stop();
        super.onStop();
    }
}