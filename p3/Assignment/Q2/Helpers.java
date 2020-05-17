import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.text.*;

public class Helpers {
    private static final int virusMin = 0, virusMax = 5;
    private static final int symptomMin = 0, symptomMax = 6;
    private static final int sNameLenMax = 250, sDescriptionLenMax = 990;
    private static final int regionMin = 0, regionMax = 11;
    private static final int percentageMin = -200, percentageMax = 200;
    public static final int mainMin = 0, mainMax = 5;

    private static final String[] vName = { "MERS-CoV", "SARS-CoV", "COVID-19", "Ebola virus", "Zika virus",
            "Human immunodeficiency viruses" };
    private static final String[] sName = { "Fever", "Cough", "Headache", "Joint and muscle aches", "Weakness",
            "Lack of appetite", "Abdominal pain" };
    private static final String[] rName = { "United States", "China", "Iran", "Nigeria", "Brazil", "Germany", "Taiwan",
            "Japan", "UK", "Canada", "India", "France" };

    public static void displayMainMenu() {
        System.out.println("Please enter number to select the option...");
        System.out.println("0. Quit");
        System.out.println("1. Query : P (Virus | Symptom)");
        System.out.println("2. Query : All Research Institutes Inventing Vaccination of a Virus");
        System.out.println("3. Insert: New Symptom");
        System.out.println("4. Modify: The Price of Vaccinations in an Region by Increasing/Decreasing Tax on Them");
        System.out.println("5. Modify: Re-open the boarder of a country again");
    }

    public static void displayMenuVirus() {
        System.out.println("Input Number to Select Virus");
        System.out.println("Nr. : Virus Name");
        for (int i = 0; i < vName.length; i++)
            System.out.println(" " + i + ". : " + vName[i]);
    }

    public static void displayMenuSymptom() {
        System.out.println("Input Number to Select Symptom");
        System.out.println("Nr. : Symptom Name");
        for (int i = 0; i < sName.length; i++)
            System.out.println(" " + i + ". : " + sName[i]);
    }

    public static void displayMenuRegion() {
        System.out.println("Input Number to Select Region");
        System.out.println("Nr. : Region Name");
        for (int i = 0; i < rName.length; i++)
            System.out.println("" + i + ". : " + rName[i]);
    }

    public static int numSanitiser(int min, int max) {
        int i = min - 1;
        for (int errorTime = 0; (!(min <= i && i <= max)) && errorTime < 5; errorTime++)  {
            System.out.println("Please enter a number between " + min + " and " + max + " (include both)");
            try {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                String s = reader.readLine();
                // the String to int conversion happens here
                i = Integer.parseInt(s.trim());
            } catch (NumberFormatException nfe) {
                System.out.println("NumberFormatException: " + nfe.getMessage());
                i = min - 1;
            } catch (IOException e) {
                System.out.println(e.getMessage());
                i = min - 1;
            }
			if (!(min <= i && i <= max))
			{
				System.out.println("Number out of range : " + i);
			}
        }
        
		return i;
	}

	public static String stringSanitiser(int length)
	{
		boolean valid = false;
        String s = "Error";

		for (int errorTime = 0; errorTime < 5 && !valid; errorTime++)
		{
			valid = true;
			System.out.println("Please enter a String shorter than " + length + 
						" (Only numbers, alphabets and whitespace are allowed)");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                s = reader.readLine();
                if (s.length() <= 0 || s.length() > length)
                {
                    valid = false;
                    System.out.println("Invalid Length : " + length);
                }

                for (int i = 0; i < s.length() && valid; i++)
                {
                    char c = s.charAt(i);
                    if ( !( ('a' <= c && c <= 'z') || ('A' <= c && c <= 'Z') || ('0' <= c && c <= '9') || c == ' ') )
                    {
                        System.out.println("Only numbers, alphabets and whitespace are allowed");
                        System.out.println("Illegal Char : " + c);
                        valid = false;
                    }
                }
            } catch (IOException e) {
                System.out.println(e.getMessage());
                s = "Error";
            }

		}
		return s;
	}

    public static void option1()
    {
        System.out.println("1. Query : P (Virus | Symptom)");
        System.out.println("Show Nothing if the Symptom Does Not Belong to the Virus");
        displayMenuVirus();
        int virusNum = numSanitiser(virusMin, virusMax);
        displayMenuSymptom();
        int symptomNum = numSanitiser(symptomMin, symptomMax);
        if (virusNum < virusMin || symptomNum < symptomMin)
        {
            System.out.println("Input Fail!");
            return;
        }
        String virus = vName[virusNum];
        String symptom = sName[symptomNum];
        Q2.query1(virus, symptom);
    }

    public static void option2()
    {
        System.out.println("2. Query : All Research Institutes Inventing Vaccination of a Virus");
        displayMenuVirus();
        int virusNum = numSanitiser(virusMin, virusMax);
        if (virusNum < virusMin)
        {
            System.out.println("Input Fail!");
            return;
        }
        String virus = vName[virusNum];
        Q2.query2(virus);
    }

    public static void option3()
    {
        System.out.println("3. Insert: New Symptom");
        System.out.println("Please Enter the Name of a new Symptom");
        String symptom = stringSanitiser(sNameLenMax);
        if (symptom.equals("Error"))
        {
            System.out.println("Input Fail!");
            return;
        }
        System.out.println("Please Enter the Description of a new Symptom");
        String description = stringSanitiser(sDescriptionLenMax);
        if (description.equals("Error"))
        {
            System.out.println("Input Fail!");
            return;
        }
        Q2.insert1(symptom, description);
    }

    public static void option4()
    {
        System.out.println("4. Modify: The Price of Vaccinations in an Region by Increasing/Decreasing Tax on Them");
        displayMenuRegion();
        int regionNum = numSanitiser(regionMin, regionMax);
        System.out.println("Please Enter the Percentage to Change. e.g. -30 will decrease the Price of vaccinations by 30%");
        int percentage = numSanitiser(percentageMin, percentageMax);
        if (regionNum < regionMin || percentage < percentageMin)
        {
            System.out.println("Input Fail!");
            return;
        }
        Q2.modify1(rName[regionNum], percentage*0.01);
    }

    public static void option5()
    {
        System.out.println("5. Modify: Re-open the boarder of a country again");
        displayMenuRegion();
        int regionNum = numSanitiser(regionMin, regionMax);
        if (regionNum < regionMin)
        {
            System.out.println("Input Fail!");
            return;
        }
        Date dNow = new Date();
        SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd");
        String today = ft.format(dNow);
        Q2.modify2(rName[regionNum], today);
    }

    // public static void main(String[] arg)
    // {
    //     try
    //     {
    //         int option = -1;
    //         numSanitiser(0, 10); 
    //         do
    //         {
    //             Helpers.displayMainMenu();
    //             option = Helpers.numSanitiser(Helpers.mainMin, Helpers.mainMax);
                
    //             if (option != 0)
    //                 System.out.println("------------------ Start Request ------------------");
    
    //             if (option == 1)
    //                 System.out.println(stringSanitiser(10));
    //             else if (option == 2)
    //                 System.out.println("------------------ 2 ------------------");
    //             else if (option == 3)
    //                 System.out.println("------------------ 3 ------------------");
    //             else if (option == 4)
    //                 System.out.println("------------------ 4 ------------------");
    //             else if (option == 5)
    //                 System.out.println("------------------ 5 ------------------");
    
    //             if (option != 0)
    //                 System.out.println("------------------ End Request ------------------");
    
    //             } while (option != 0);
    //     }
    //     finally
    //     {
    //         System.out.println("------------------ Bye! ------------------");
    //     }
        
    // }
}
