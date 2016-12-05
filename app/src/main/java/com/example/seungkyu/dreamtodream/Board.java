package com.example.seungkyu.dreamtodream;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

public class Board extends AppCompatActivity {
    BoardListViewAdapter adapter;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_board);

        adapter = new BoardListViewAdapter();
        // 리스트뷰 참조 및 Adapter달기
        listview = (ListView) findViewById(R.id.boardLV);
        listview.setAdapter(adapter);

        adapter.addItem("게시판 ㅅㅂ 되냐?", "seungkyu","2016-12-03");
        adapter.addItem("미밴드 거의 쌔삥 살사람 있냐?", "junyep","2016-12-03");
        adapter.addItem("이거 앱 좋음?", "seungkyu","2016-12-03");
        adapter.addItem("와 3시간 깊으잠 개이득", "gildong","2016-12-03");
        adapter.addItem("미밴드 공동구매 하실분 구해요^^", "닉넴멀로하지","2016-12-03");
        adapter.addItem("그냥 심심해서 써봄", "jooyoung","2016-12-03");
        adapter.addItem("아 ㅡㅡ 님들 질문있음", "solder76","2016-12-03");
        adapter.addItem("이거 어캐함?", "루시우","2016-12-03");
        adapter.addItem("오버워치 같이 하실분 구함", "파라","2016-12-03");
        adapter.addItem("롤 캐리해드림", "메르띠","2016-12-03");
        adapter.addItem("안녕하세요ㅎㅎ", "라인하르트","2016-12-03");
        adapter.addItem("어디로 가야 하오", "리신","2016-12-03");
        adapter.addItem("내 이름은 맥크리", "맥크리","2016-12-03");

        listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView parent, View v, int position, long id) {
                // get item
                BoardVO item = (BoardVO) parent.getItemAtPosition(position);
                Intent intent = new Intent(getApplicationContext(), BoardViewPage.class);
                intent.putExtra("title",item.getTitle());
                intent.putExtra("writer",item.getAuthor());
                intent.putExtra("date",item.getDate());
                intent.putExtra("content","여기부터 본문입니다.\n댓글은 또 언제 구현하나...");
                startActivity(intent);
            }
        });
    }
}
