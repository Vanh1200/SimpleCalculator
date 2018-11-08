package com.example.vanh1200.simplecalculator;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    public static final int NUMBER_FIRST = 1;
    public static final int NUMBER_SECOND = 2;
    public static final int OPERATION_PLUS = 1;
    public static final int OPERATION_MINUS = 2;
    public static final int OPERATION_DIVIDE = 3;
    public static final int OPERATION_MULTIPLY = 4;
    public static final int OPERATION_DEFAULT = 0;
    public static final int OPERATION_TYPE = 1;
    public static final int OTHER_TYPE = 2;
    public static final double NUMBER_ZERO = 0;
    public static final double NUMBER_HUNDRED = 100;
    public static final String DEFAULT_RESULT = "0";
    public static final String CHARACTER_MINUS = "-";
    public static final String CHARACTER_DOT = ".";

    private LinearLayout mLinearTextZero;
    private TextView mTextOne;
    private TextView mTextTwo;
    private TextView mTextThree;
    private TextView mTextFour;
    private TextView mTextFive;
    private TextView mTextSix;
    private TextView mTextSeven;
    private TextView mTextEight;
    private TextView mTextNine;

    private TextView mTextPlus;
    private TextView mTextMinus;
    private TextView mTextMultiply;
    private TextView mTextDivide;
    private TextView mTextEqual;
    private TextView mTextPlusMinus;
    private TextView mTextPercent;
    private TextView mTextAc;
    private TextView mTextDot;
    private TextView mTextResult;

    private Double mFirstNumber;
    private Double mSecondNumber;
    private int mCurrentOperation;
    private int mCurrentNumber;
    private boolean mIsNewNumber;
    private int mPrevButton;
    private int mCurrentButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initViews();
        registerEvents();
        initDefaultValues();
    }

    private void initDefaultValues() {
        mIsNewNumber = true;
        mCurrentNumber = NUMBER_FIRST;
        mFirstNumber = NUMBER_ZERO;
        setSelectedOperation(mCurrentOperation, false);
        mCurrentOperation = OPERATION_DEFAULT;
        mCurrentButton = OTHER_TYPE;
    }

    private void registerEvents() {
        mLinearTextZero.setOnClickListener(this);
        mTextOne.setOnClickListener(this);
        mTextTwo.setOnClickListener(this);
        mTextThree.setOnClickListener(this);
        mTextFour.setOnClickListener(this);
        mTextFive.setOnClickListener(this);
        mTextSix.setOnClickListener(this);
        mTextSeven.setOnClickListener(this);
        mTextEight.setOnClickListener(this);
        mTextNine.setOnClickListener(this);
        mTextPlus.setOnClickListener(this);
        mTextMultiply.setOnClickListener(this);
        mTextMinus.setOnClickListener(this);
        mTextDivide.setOnClickListener(this);
        mTextPlusMinus.setOnClickListener(this);
        mTextEqual.setOnClickListener(this);
        mTextPercent.setOnClickListener(this);
        mTextAc.setOnClickListener(this);
        mTextDot.setOnClickListener(this);
    }

    private void initViews() {
        mLinearTextZero = findViewById(R.id.linear_text_0);
        mTextOne = findViewById(R.id.text_1);
        mTextTwo = findViewById(R.id.text_2);
        mTextThree = findViewById(R.id.text_3);
        mTextFour = findViewById(R.id.text_4);
        mTextFive = findViewById(R.id.text_5);
        mTextSix = findViewById(R.id.text_6);
        mTextSeven = findViewById(R.id.text_7);
        mTextEight = findViewById(R.id.text_8);
        mTextNine = findViewById(R.id.text_9);
        mTextPlus = findViewById(R.id.text_plus);
        mTextMinus = findViewById(R.id.text_minus);
        mTextMultiply = findViewById(R.id.text_multiply);
        mTextDivide = findViewById(R.id.text_divide);
        mTextEqual = findViewById(R.id.text_equal);
        mTextPlusMinus = findViewById(R.id.text_plus_and_minus);
        mTextPercent = findViewById(R.id.text_percent);
        mTextAc = findViewById(R.id.text_ac);
        mTextDot = findViewById(R.id.text_dot);
        mTextResult = findViewById(R.id.text_result);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.text_ac:
                clickAC();
                break;
            case R.id.text_1:
                clickNumber(1);
                break;
            case R.id.text_2:
                clickNumber(2);
                break;
            case R.id.text_3:
                clickNumber(3);
                break;
            case R.id.text_4:
                clickNumber(4);
                break;
            case R.id.text_5:
                clickNumber(5);
                break;
            case R.id.text_6:
                clickNumber(6);
                break;
            case R.id.text_7:
                clickNumber(7);
                break;
            case R.id.text_8:
                clickNumber(8);
                break;
            case R.id.text_9:
                clickNumber(9);
                break;
            case R.id.linear_text_0:
                clickNumber(0);
                break;
            case R.id.text_dot:
                clickDot();
                break;
            case R.id.text_plus_and_minus:
                clickPlusAndMinus();
                break;
            case R.id.text_percent:
                clickPercent();
                break;
            case R.id.text_plus:
                clickOperation(OPERATION_PLUS);
                break;
            case R.id.text_minus:
                clickOperation(OPERATION_MINUS);
                break;
            case R.id.text_divide:
                clickOperation(OPERATION_DIVIDE);
                break;
            case R.id.text_multiply:
                clickOperation(OPERATION_MULTIPLY);
                break;
            case R.id.text_equal:
                clickEqual();
                break;
        }
    }

    private void clickAC() {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        mTextResult.setText(DEFAULT_RESULT);
        initDefaultValues();
    }

    private void clickEqual() {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        String result = mTextResult.getText().toString();
        mSecondNumber = Double.parseDouble(result);
        mIsNewNumber = true;
        if (mCurrentNumber == NUMBER_FIRST) {
            mTextResult.setText(result);
            return;
        }
        try {
            double resultDouble = calculate(mFirstNumber, mSecondNumber, mCurrentOperation);
            mTextResult.setText(String.valueOf(resultDouble));
            mFirstNumber = resultDouble;
            mCurrentNumber = NUMBER_FIRST;
            setSelectedOperation(mCurrentOperation, false);
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.error_divide_by_zero),
                    Toast.LENGTH_SHORT).show();
        }
    }

    private double calculate(double firstNumber, double secondNumber, int operation)
            throws IllegalArgumentException {
        switch (operation) {
            case OPERATION_PLUS:
                return firstNumber + secondNumber;
            case OPERATION_MINUS:
                return firstNumber - secondNumber;
            case OPERATION_DIVIDE:
                if (secondNumber == NUMBER_ZERO)
                    throw new IllegalArgumentException(getString(R.string.error_divide_by_zero));
                return firstNumber / secondNumber;
            case OPERATION_MULTIPLY:
                return firstNumber * secondNumber;
            default:
                break;
        }
        return 0;
    }

    private void clickOperation(int operation) {
        mPrevButton = mCurrentButton;
        mCurrentButton = OPERATION_TYPE;
        setSelectedOperation(mCurrentOperation, false);
        setSelectedOperation(operation, true);
        mIsNewNumber = true;
        String result = mTextResult.getText().toString();
        if (mCurrentNumber == NUMBER_FIRST) {
            mCurrentOperation = operation;
            mFirstNumber = Double.parseDouble(result);
            mCurrentNumber = NUMBER_SECOND;
            return;
        }
        if (mPrevButton != OPERATION_TYPE) {
            handleContinousMath(operation);
            return;
        }
        mCurrentOperation = operation;
    }

    private void handleContinousMath(int operation) {
        String result = mTextResult.getText().toString();
        mSecondNumber = Double.parseDouble(result);
        mIsNewNumber = true;
        try {
            double resultDouble = calculate(mFirstNumber, mSecondNumber, mCurrentOperation);
            mTextResult.setText(String.valueOf(resultDouble));
            mFirstNumber = resultDouble;
            mCurrentNumber = NUMBER_SECOND;
        } catch (Exception e) {
            Toast.makeText(this, getString(R.string.error_divide_by_zero),
                    Toast.LENGTH_SHORT).show();
        }
        mCurrentOperation = operation;
    }

    private void setSelectedOperation(int operation, boolean isSelected) {
        switch (operation) {
            case OPERATION_PLUS:
                mTextPlus.setSelected(isSelected);
                break;
            case OPERATION_MINUS:
                mTextMinus.setSelected(isSelected);
                break;
            case OPERATION_DIVIDE:
                mTextDivide.setSelected(isSelected);
                break;
            case OPERATION_MULTIPLY:
                mTextMultiply.setSelected(isSelected);
                break;
            default:
                break;
        }
    }

    private void clickPercent() {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        mIsNewNumber = true;
        String result = mTextResult.getText().toString();
        double convertedResult = Double.parseDouble(result);
        mTextResult.setText(String.valueOf(convertedResult / NUMBER_HUNDRED));
    }

    private void clickPlusAndMinus() {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        String result = mTextResult.getText().toString();
        if (result.equals(DEFAULT_RESULT)) return;
        if (result.contains(CHARACTER_MINUS)) mTextResult.setText(result.substring(1));
        else mTextResult.setText(CHARACTER_MINUS + result);
    }

    private void clickDot() {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        if (mIsNewNumber)
            mTextResult.setText(concatString(DEFAULT_RESULT, CHARACTER_DOT));
        else {
            String result = mTextResult.getText().toString();
            if (result.contains(CHARACTER_DOT)) return;
            mTextResult.setText(result + CHARACTER_DOT);
        }
        mIsNewNumber = false;
    }

    void clickNumber(int i) {
        mPrevButton = mCurrentButton;
        mCurrentButton = OTHER_TYPE;
        String result = mTextResult.getText().toString();
        if (mIsNewNumber || result.equals(DEFAULT_RESULT)) {
            mTextResult.setText(String.valueOf(i));
            mIsNewNumber = false;
        } else mTextResult.setText(result + String.valueOf(i));
    }

    private String concatString(String... strings) {
        StringBuilder builder = new StringBuilder();
        for (String string : strings) {
            builder.append(string);
        }
        return builder.toString();
    }
}
