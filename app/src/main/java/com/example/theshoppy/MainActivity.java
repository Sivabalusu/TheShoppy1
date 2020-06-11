package com.example.theshoppy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity
{
    //Required UI view variables
    AutoCompleteTextView provinces;
    EditText etDate;
    EditText name;
    TextView output,tvDesktop,tvLaptop;
    DatePickerDialog datePicker;
    CheckBox chkLaptop,chkDesktop,chkSSD,chkPrinter;
    RadioGroup rgLaptop,rgDesktop;
    RadioButton rgMat,rgUSB,rgLapStand,rgWebcam,rgHDD;
    Button btnBuy;
    Spinner spnBrand;

    //Required primitive variable to store data
    String[] provinceList={"Alberta","British Columbia","Manitoba","New Brunswick","Newfoundland and Labrador"
            ,"Nova Scotia", "Ontario","Prince Edward Island","Quebec","Saskatchewan","Yukon"};
    String[] brand={"Dell","HP","Lenovo"};
    String selectedBrand;
    double cost=0;
    String computer,additional,additionalPeripheral;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Intializing UI Varibles to theie corresponding resource id's
        chkDesktop=(CheckBox)findViewById(R.id.chkDesktop);
        chkLaptop=(CheckBox)findViewById(R.id.chkLaptop);
        chkSSD=(CheckBox)findViewById(R.id.chkssd);
        chkPrinter=(CheckBox)findViewById(R.id.chkPrinter);
        rgLaptop=(RadioGroup)findViewById(R.id.radLaptop);
        rgDesktop=(RadioGroup)findViewById(R.id.radDesktop);
        rgMat=(RadioButton)findViewById(R.id.radMat);
        rgUSB=(RadioButton)findViewById(R.id.radUsb);
        rgLapStand=(RadioButton)findViewById(R.id.radStand);
        rgWebcam=(RadioButton)findViewById(R.id.radWebcam);
        rgHDD=(RadioButton)findViewById(R.id.radHdd);
        btnBuy=(Button)findViewById(R.id.btnBuy);
        name=(EditText) findViewById(R.id.etName);
        output=(TextView)findViewById(R.id.tvOut);
        tvDesktop=(TextView)findViewById(R.id.tvDeskTopPeripherals);
        tvLaptop=(TextView)findViewById(R.id.tvLaptopPeripherals);
        provinces=(AutoCompleteTextView) findViewById(R.id.atvProvince);
        etDate=(EditText)findViewById(R.id.etDate);
        spnBrand=(Spinner)findViewById(R.id.spnBrand);

        //Providing values to AutoCompleteTextView by using ArrayAdapter
        ArrayAdapter<String> provinceAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_dropdown_item_1line,provinceList);
        provinces.setAdapter(provinceAdapter);

        //Assigning Date Picker to EdiText
        etDate.setInputType(InputType.TYPE_NULL);
        etDate.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                final Calendar cal = Calendar.getInstance();
                int day=cal.get(Calendar.DAY_OF_MONTH);
                int month=cal.get(Calendar.MONTH);
                int year=cal.get(Calendar.YEAR);
                datePicker=new DatePickerDialog(MainActivity.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                                etDate.setText(dayOfMonth+"/"+month+1+"/"+year);
                            }
                        },year,month,day);
                datePicker.show();
            }
        });



        //Assigning Brand string to Spinner
        ArrayAdapter<String> brands=new ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,brand);
        spnBrand.setAdapter(brands);
        spnBrand.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selectedBrand=brand[position];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });


        btnBuy.setOnClickListener(new View.OnClickListener() { //Calling button click function
            @Override
            public void onClick(View v)
            {
                //Finding cost of Computer selected
                if(computer.equals("Laptop")&&selectedBrand.equals("Dell"))
                {
                    cost=cost+1249;
                }
                else if(computer.equals("Laptop")&&selectedBrand.equals("HP"))
                {
                    cost=cost+1150;
                }
                else if(computer.equals("Laptop")&&selectedBrand.equals("Lenovo"))
                {
                    cost=cost+1549;
                }
                else if(computer.equals("Desktop")&&selectedBrand.equals("Dell"))
                {
                    cost=cost+475;
                }
                else if(computer.equals("Desktop")&&selectedBrand.equals("HP"))
                {
                    cost=cost+400;
                }
                else if(computer.equals("Desktop")&&selectedBrand.equals("Lenovo"))
                {
                    cost=cost+450;
                }
                //Adding additional accessories cost
                if(chkPrinter.isChecked())
                {
                    cost=cost+100;
                    additional="Printer";
                }
                if(chkSSD.isChecked())
                {
                    cost=cost+60;
                    additional="SSD";
                }

                output.setVisibility(View.VISIBLE);//Enabling the output TextView
                double tax=cost*0.13;
                cost=cost+tax;
                output.setText("Customer: "+name.getText()+"\n"+"Province: "+provinces.getText()+"\n"+
                       "Date Of Purchase:"+etDate.getText()+"\n"+ "Computer:"+computer+"\n"+
                       "Brand: "+selectedBrand+"\n"+ "Additional: "+additional+"\n"+
                       "Added Peripherals: "+additionalPeripheral+"\n"+ "Cost: "+cost);

            }
        });

    }

    public void onchkComputerClick(View view)
    {
        //Getting checked Checkbox value to computer String
        if(chkDesktop.isChecked())
        {
            computer="DeskTop";                     //If User selects Desktop then
            rgLaptop.setVisibility(View.INVISIBLE); //Hiding the Laptop's Additional Peripherals
            rgDesktop.setVisibility(View.VISIBLE);
            tvLaptop.setVisibility(View.INVISIBLE);
            tvDesktop.setVisibility(View.VISIBLE);

        }
        if(chkLaptop.isChecked())
        {
            computer="Laptop";                   //If User selects Desktop then
            rgDesktop.setVisibility(View.INVISIBLE);  //Hiding the Laptop's Additional Peripherals
            rgLaptop.setVisibility(View.VISIBLE);
            tvDesktop.setVisibility(View.INVISIBLE);
            tvLaptop.setVisibility(View.VISIBLE);
        }
    }

    public void onRadSelected(View v)
    {
        if(rgHDD.isChecked())
        {
            cost=cost+64;
            additionalPeripheral="External Hard Drive";
        }
        if(rgWebcam.isChecked())
        {
            cost=cost+95;
            additionalPeripheral="Webcam";
        }
        if(rgMat.isChecked())
        {
            cost=cost+33;
            additionalPeripheral="Cooling Mat";
        }
        if(rgUSB.isChecked())
        {
            cost=cost+60;
            additionalPeripheral="USB C-Hub";
        }
        if(rgLapStand.isChecked())
        {
            cost=cost+139;
            additionalPeripheral="Laptop Stand";
        }
    }

}
