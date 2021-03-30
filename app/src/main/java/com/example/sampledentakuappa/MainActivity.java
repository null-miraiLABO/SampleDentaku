package com.example.sampledentakuappa;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //ArrayListクラスのオブジェクトを作成。構文)List<型>変数名=new ArrayList<>
        List<Button>buttonList=new ArrayList<>();

        //変数名.add　で要素を追加。
        //記号、C +/- % DEL .
        buttonList.add((Button)findViewById(R.id.button));
        buttonList.add((Button)findViewById(R.id.button2));
        buttonList.add((Button)findViewById(R.id.button3));
        buttonList.add((Button)findViewById(R.id.button4));
        buttonList.add((Button)findViewById(R.id.button19));
        //数字、9 ~ 0
        buttonList.add((Button)findViewById(R.id.button5));
        buttonList.add((Button)findViewById(R.id.button6));
        buttonList.add((Button)findViewById(R.id.button7));
        buttonList.add((Button)findViewById(R.id.button8));
        buttonList.add((Button)findViewById(R.id.button9));
        buttonList.add((Button)findViewById(R.id.button10));
        buttonList.add((Button)findViewById(R.id.button11));
        buttonList.add((Button)findViewById(R.id.button12));
        buttonList.add((Button)findViewById(R.id.button13));
        buttonList.add((Button)findViewById(R.id.button14));
        //記号、/ * - + =
        buttonList.add((Button)findViewById(R.id.button15));
        buttonList.add((Button)findViewById(R.id.button16));
        buttonList.add((Button)findViewById(R.id.button17));
        buttonList.add((Button)findViewById(R.id.button18));
        buttonList.add((Button)findViewById(R.id.button20));

        //意味わからん
        ButtonListener listener=new ButtonListener();
        for (Button button:buttonList){
            button.setOnClickListener(listener);
        }
    }

    //インターフェース。各ボタンの仕様を定義。
    private class ButtonListener implements View.OnClickListener{
        private List<BigDecimal>_numlist=new ArrayList<>();
        private List<Character>_opeList=new ArrayList<>();
        private String _inputValue="";

        @Override
        public void onClick(View view){
            TextView tvFormula=findViewById(R.id.textView2);

            //ボタン間の処理を定義
            int btId=view.getId();
            char inputChar;
            switch (btId){
                //数字
                case R.id.button5:
                    inputChar='9';//charは一文字
                    addTextView(tvFormula,inputChar);//addTextViewメゾットに、tvFormula,inputCharを渡す。
                    _inputValue += inputChar;// _inputValueにinputCharを足す。n桁の数字を表現。
                    break;
                case R.id.button6:
                    inputChar='8';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button7:
                    inputChar='7';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button8:
                    inputChar='6';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button9:
                    inputChar='5';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button10:
                    inputChar='4';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button11:
                    inputChar='3';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button12:
                    inputChar='2';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button13:
                    inputChar='1';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button14:
                    inputChar='0';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                    //記号
                case R.id.button15:
                    inputChar='/';
                    if (!(_inputValue.equals(""))){//もし数字が空じゃなっかったなら。
                        addList(tvFormula,_inputValue,inputChar);//(前の数字,演算子,次に入れた数字)
                    }
                    break;
                case R.id.button16:
                    inputChar='*';
                    if (!(_inputValue.equals(""))){
                        addList(tvFormula,_inputValue,inputChar);
                    }
                    break;
                case R.id.button17:
                    inputChar='-';
                    if (!(_inputValue.equals(""))){
                        addList(tvFormula,_inputValue,inputChar);
                    }
                    break;
                case R.id.button18:
                    inputChar='+';
                    if (!(_inputValue.equals(""))){
                        addList(tvFormula,_inputValue,inputChar);
                    }
                    break;
                case R.id.button20:
                    inputChar='=';
                    if (!(_inputValue.equals(""))){
                        addList(tvFormula,_inputValue,inputChar);
                    }
                    String result=calculate().toString();
                    tvFormula.setText(result);
                    _inputValue=result;
                    _numlist.clear();
                    _opeList.clear();
                    break;
                case R.id.button:
                    tvFormula.setText("");
                    _numlist.clear();
                    break;
                case R.id.button4:
                    String formulaStr=tvFormula.getText().toString();
                    char formulaStrLastChar=formulaStr.charAt(formulaStr.length()-1);

                    if (isFourArithmeticOpe(formulaStrLastChar)){
                        _opeList.remove(_opeList.size()-1);
                    }
                    if (!formulaStr.isEmpty()){
                        tvFormula.setText(formulaStr.subSequence(0,formulaStr.length()-1));
                    }
                    if (!_inputValue.isEmpty()){
                        _inputValue=_inputValue.substring(0,_inputValue.length()-1);
                    }
                    break;
                case R.id.button19:
                    inputChar='.';
                    addTextView(tvFormula,inputChar);
                    _inputValue+=inputChar;
                    break;
                case R.id.button2:
                    break;
                case R.id.button3:
                    break;
            }
        }

        //押されたボタンに定義された情報を、各Listに格納している？
        private void addList(TextView tvFormula,String inputValue,char ope){
            addTextView(tvFormula,ope);
            _numlist.add(new BigDecimal(inputValue));
            _opeList.add(ope);
            _inputValue="";
        }

        //表示するメゾット
        private void addTextView(TextView textView,char addStr){
            textView.setText(textView.getText().toString()+addStr);
        }

        //計算処理をするメゾット
        private BigDecimal calculate(){
            int i=0;

            while (i<_opeList.size()){
                if (_opeList.get(i)=='*'|_opeList.get(i)=='/'){
                    BigDecimal resultMultiDiv=_opeList.get(i)=='*'?_numlist.get(i).multiply(_numlist.get(i+1)):_numlist.get(i).divide(_numlist.get(i+1));

                    _numlist.set(i,resultMultiDiv);
                    _numlist.remove(i+1);
                    _opeList.remove(i);
                    i--;
                }
                else if (_opeList.get(i)=='-'){
                    _opeList.set(i,'+');
                    _numlist.set(i+1,_numlist.get(i+1).negate());
                }
                i++;
            }

            BigDecimal result=new BigDecimal("0");
            for (BigDecimal j:_numlist){
                result=result.add(j);
            }
            return result;
        }

        //
        private boolean isFourArithmeticOpe(char c){
            if (c=='+'|c=='-'|c=='*'|c=='/')return true;
            return false;
        }
    }
}

