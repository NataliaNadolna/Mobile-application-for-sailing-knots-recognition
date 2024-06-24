package com.example.wzy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {
    private RecyclerView.Adapter adapter;
    private RecyclerView recyclerViewList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        recyclerView();
        bottomNavigation();
    }

    private void bottomNavigation(){
        FloatingActionButton floatingActionButton = findViewById(R.id.homeBtn);
        LinearLayout listaBtn = findViewById(R.id.listaBtn);
        LinearLayout galeriaBtn = findViewById(R.id.galeriaBtn);
        LinearLayout aparatBtn = findViewById(R.id.aparatBtn);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, ListActivity.class));
            }
        });
        listaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, ListActivity.class));
            }
        });
        galeriaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, MainActivity.class));
            }
        });
        aparatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListActivity.this, GalleryActivity.class));
            }
        });
    }

    private void recyclerView(){
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerViewList = findViewById(R.id.recyclerView);
        recyclerViewList.setLayoutManager(linearLayoutManager);

        ArrayList<Knot> knotList = new ArrayList<>();
        knotList.add(new Knot(
                "Zwykły",
                "p_zwykly",
                "Stoper",
                "Węzeł zwykły (półsztyk) jest najprostszym rodzajem węzła. " +
                        "Jest stosowany jako węzeł zabezpieczający inny węzeł przed rozwiązaniem. " +
                        "Czasem stosuje się go również jako zabezpieczenie liny przed wysunięciem się z bloczka lub kipy, " +
                        "jednak w tym celu lepiej jest zastosować ósemkę. ",
                "android.resource://" + getPackageName() + "/" + R.raw.f_zwykly));

        knotList.add(new Knot(
                "Ósemka",
                "p_osemka",
                "Stoper",
                "Ósemka jest węzłem stosowanym do zabezpieczenia końca liny przed wysunięciem się z otworów takich jak bloczek lub kipa. " +
                        "Wiąże się ją na końcach szotów oraz fałów. " +
                        "Kształtem węzeł przypomina cyfrę 8.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_osemka));

        knotList.add(new Knot(
                "Prosty",
                "p_prosty",
                "Do łączenia lin",
                "Węzeł prosty jest stosowany do łączenia dwóch, niezbyt obciążonych lin o zbliżonych średnicach. " +
                        "Przy dużych obciążeniach węzeł ten jest trudny do rozwiązania. " +
                        "Odmianą węzła prostego jest węzeł refowy.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_prosty));

        knotList.add(new Knot(
                "Szotowy",
                "p_szotowy",
                "Do łączenia lin",
                "Węzeł szotowy służy do łączenia lin o różnych średnicach. " +
                        "Nie nadaje się do wiązania gładkich lin o dużym naciągu. " +
                        "Odmianą węzła szotowego jest węzeł flagowy.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_szotowy));

        knotList.add(new Knot(
                "Ratowniczy",
                "p_ratowniczy",
                "Z nieruchomą pętlą",
                "Węzeł ratowniczy jest jednym z najważniejszych węzłów żeglarskich o uniwersalnym zastosowaniu. " +
                        "Jest szczególnie przydatny, kiedy należy zawiązać niezaciskającą się pętlę. " +
                        "Wbrew nazwy nie jest używany do ratowania. Jest łatwy do rozwiązania, " +
                        "nie zaciska się nawet pod bardzo dużym obciążeniem.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_ratowniczy));


        knotList.add(new Knot(
                "Wyblinka",
                "p_wyblinka",
                "Z zaciskającą się pętlą",
                "Wyblinka to zaciskająca się pętla stworzona z dwóch półsztyków. " +
                        "Może być stosowana do cumowania jachtu, przywiązywania odbijaczy oraz zbuchtowanych lin. " +
                        "Pod dużym obciążeniem wyblinka może bardzo mocno się zacisnąć, przez co może być trudna do rozwiązania.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_wyblinka) );

        knotList.add(new Knot(
                "Rożkowy",
                "p_rozkowy",
                "Do zabezpieczenia liny",
                "Węzeł rożkowy wykorzystywany jest do wiązania fałów przed wypadnięciem z bloku topowego. " +
                        "Chroni przed ucieczką fała w kierunku topu masztu. "  +
                        "Może także służyć jako przytrzymanie liny zdwojonej, która jest prowadzona na bloku. " +
                        "Jest to także znakomite przytrzymanie liny na drugiej linie.",
                "android.resource://" + getPackageName() + "/" + R.raw.f_rozkowy));

        knotList.add(new Knot(
                "Refowy",
                "p_refowy",
                "",
                "Węzeł refowy jest modyfikacją węzła prostego. Różnicą jest pozostawienie dodatkowej pętelki z liny w węźle refowym." +
                        "Pętelka powoduje, że węzeł jest łatwy do rozwiązania nawet po mocnym zaciśnięcu. " +
                        "Dlatego najczęściej stosowany jest przy refowaniu żagli",
                "android.resource://" + getPackageName() + "/" + R.raw.f_refowy));

        knotList.add(new Knot(
                "Flagowy",
                "p_flagowy",
                "",
                "W żeglarstwie węzeł flagowy stosowany jest do wiązania flagi do jej flaglinki. " +
                        "Od tego zastosowania wzięła się nazwa węzła. " +
                        "Węzeł flagowy wiąże się podobnie do węzła szotowego. W ostatnim kroku nie przeciąga się całej liny, tylko jej fragment, tworząc w ten sposób pętelkę. " +
                        "Utworzona pętla sprawia, że węzeł jest prosty do rozwiązania nawet po dużym obciążeniu. ",
                "android.resource://" + getPackageName() + "/" + R.raw.f_flagowy));


        adapter = new KnotAdapter(knotList);
        recyclerViewList.setAdapter(adapter);
    }



}