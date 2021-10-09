package com.example.mortagecalc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.example.mortagecalc.databinding.FragmentSecondBinding;

import java.text.DecimalFormat;

public class SecondFragment extends Fragment {

    private FragmentSecondBinding binding;
    double mortgageAmount; //Initializing variable names
    double intrestAmount;
    double mortgageTenure;

    EditText mortgage_amount_input; //Initializing variable names
    EditText intrest_rate_input;
    EditText mortgage_tenure_input;

    TextView showMonthly; //Initializing variable names


    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {

        View fragmentSecondLayout = inflater.inflate(R.layout.fragment_second, container, false);
        showMonthly = fragmentSecondLayout.findViewById(R.id.monthly_output);//Used to update the output as user pressed calcualte button
        return fragmentSecondLayout;

    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mortgage_amount_input = (EditText) view.findViewById(R.id.mortgage_amount_input); //Grabs the entered amount by the user
        intrest_rate_input = (EditText) view.findViewById(R.id.intrest_rate_input); //Grabs the entered amount by the user
        mortgage_tenure_input = (EditText) view.findViewById(R.id.mortgage_tenure_input); //Grabs the entered amount by the user
        view.findViewById(R.id.calculate_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mortgageAmount = Double.parseDouble(mortgage_amount_input.getText().toString()); //Gets the entered amount from the user ready to be used for calculation
                intrestAmount = Double.parseDouble(intrest_rate_input.getText().toString()); //Gets the entered amount from the user ready to be used for calculation
                mortgageTenure = Double.parseDouble(mortgage_tenure_input.getText().toString()); //Gets the entered amount from the user ready to be used for calculation
                EMI(mortgageAmount, intrestAmount, mortgageTenure); //Sends value to EMI method to be calculated
            }
        });

    }

    private void EMI(double P, double r, double n) //Here is where the calculation is done
    {
        String showCount = showMonthly.getText().toString();
        double rate= r/(12*100);
        double EMI = (P*rate*Math.pow(1+rate,n))/(Math.pow(1+rate,n)-1);
        DecimalFormat decimalFormat = new DecimalFormat();
        decimalFormat.setMaximumFractionDigits(2);
        showMonthly.setText("$"+(decimalFormat.format(EMI)));
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}