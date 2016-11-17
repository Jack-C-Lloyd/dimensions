package CI346.dimensions;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.Unit;
import javax.measure.unit.UnitFormat;

import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;
import static org.jscience.economics.money.Currency.EUR;
import static org.jscience.economics.money.Currency.GBP;
import static org.jscience.economics.money.Currency.USD;


public class Main {

    public static void main(String[] args) {

        System.out.println("Money/Currencies");
        ///////////////////////////////////////////////////////////////////////
        // Calculates the cost of a car trip in Europe for an American tourist.
        ///////////////////////////////////////////////////////////////////////

        // Use currency symbols instead of ISO-4217 codes.
        UnitFormat.getInstance().label(USD, "$"); // Use "$" symbol instead of currency code ("USD")
        UnitFormat.getInstance().label(EUR, "€"); // Use "€" symbol instead of currency code ("EUR")

        // Sets exchange rates.
        Currency.setReferenceCurrency(USD);
        EUR.setExchangeRate(1.07); // 1.0 € = 1.07 $

        // Calculates trip cost.
        Unit<?> mpg = MILE.divide(GALLON_LIQUID_US);
        Amount<?> carMileage = Amount.valueOf(20, mpg); // 20 mi/gal.
        Amount<?> petrolPrice = Amount.valueOf(1.2, EUR.divide(LITRE)); // 1.2 €/L

        Amount<Length> tripDistance = Amount.valueOf(400, KILO(METRE)); // 400 km
        Amount<Money> tripCost = tripDistance.divide(carMileage).times(petrolPrice).to(USD);

        System.out.println("Trip cost = " + tripCost + " (" + tripCost.to(EUR) + ")");

        /*
        1. Write code using the same methods as above to calculate the cost of a car journey
        from New York to San Jose in dollars, and convert it to British currency before printing
        out the result. You'll need to check the exchange rate for Sterling, i.e.
        how many dollars you get for one pound, and set the exchange rate in the class representing
        British currency (see the Euros example).
         */

        /*
        2. Convert a value in the SI unit of specific impulse N.s/kg (impulse per kg of fuel) to
        its US equivalent lbf.s/lb. The specific impulse of the Orbiter was 3041 N.s/kg. Just to
        check that this all works, what happens if you try to convert 3041 N.s/kg to an incompatible
        unit such as lbf.s ?
         */

    }
}
