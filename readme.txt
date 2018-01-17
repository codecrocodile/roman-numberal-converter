==Exercise Notes==

This project is written using Maven build tool. I also used Eclipse for my IDE. I have left the Eclipse
project files intact as the specification states "an eclipse project would ideal". Although you may want 
to delete these and import as Maven project if you have a Maven plugin.


===Implementation===

The exercise really didn't take that much time at all, therefore in addition to the specification requirements of 
taking numbers written in the Arabic numeral system and converting them to the Roman numeral system I have also 
implemented taking numbers written in the Roman numeral system and converts them to the Arabic numeral system. 

The class implementing this functionality is:
com.codecrocodile.NumeralConverter 

As a further addition I have written a small GUI client for you to test this on. The main method for this is in:
com.codecrocodile.client.Start


===Development Testing===

I have written some tests for both the Roman to Arabic and Arabic to Roman conversions. These can be run using 
maven by calling "mvn test" from the project root directory.


===Arabic to Roman Conversion Explained===

NumeralConverter.convertToRomanNumerals(int arabicNumerals)

Roman Numerals are always written with the shortest number of numerals as possible. Given the additive and 
subtractive rules for valid Roman numerals, I created in a map, all the highest values from these rules (which 
I have called the major values). I then decrement the input by  the major values, adding corresponding Roman 
numeral to the output, until we have Roman numerals with the shortest possible representation for the input value.


===Roman to Arabic Conversion Explained===

NumeralConverter.convertToArabicNumerals(String romanNumerals)

I felt it import in this implementation to keep a clean separation between validating the input and doing the 
actual conversion. This make the code easier to read in my opinion. I first validated that the input was indeed 
a valid format for a Roman numeral number using regex. If the input was a single Roman numeral character  then I 
just returned the Arabic numeral value from my major increments map, otherwise I iterate through the characters 
of the input taking the current character and the preceding character. I then test for additive or subtractive 
rule based on these characters and use the corresponding Arabic value to add or subtract from the result.



I hope this goes just a little way in demonstrating that I can program. Take a risk I wont disappoint :-) I look 
forward to hearing from you soon.


Chris  
