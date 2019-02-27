package com.congwiny.basic.clazzinterface.nestedclass;

public class NestedClassTest {
    public static void main(String[] args){
        Button.ButtonState state = new Button.ButtonState();

        Button.ButtonState2 state2 = new Button().new ButtonState2();
        state2.accessOutter();

        Button btn = new Button();
        btn.setOnClickListener();
        btn.getMListener().onClick(null);
    }
}
