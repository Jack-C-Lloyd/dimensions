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
        UnitFormat.getInstance().label(GBP, "£"); // Use "€" symbol instead of currency code ("EUR")

        // Sets exchange rates.
        Currency.setReferenceCurrency(USD);
        EUR.setExchangeRate(1.07); // 1.0 € = 1.07 $

        // Calculates trip cost.
        Unit<?> mpg = MILE.divide(GALLON_LIQUID_US);
        Amount<?> carMileage = Amount.valueOf(20, mpg); // 20 mi/gal.
        Amount<?> petrolPrice = Amount.valueOf(1.2, EUR.divide(LITRE)); // 1.2 €/L

        Amount<Length> tripDistance = Amount.valueOf(400, KILO(METRE)); // 400 km
        Amount<Money> tripCost = tripDistance.divide(carMileage).times(petrolPrice).to(USD);

        System.out.println("Trip cost = " + tripCost + " ("
                + tripCost.to(EUR) + ")");

        /*
        1. Write code using the same methods as above to calculate the cost of a car journey
        from New York to San Jose. You'll need to check the exchange rate for Sterling, i.e.
        how many pounds you get for one dollar.
         */

        /*
        2. Convert the NonSI unit lbf.s/lb of specific impulse (impulse per pound of fuel) to
        its SI equivalent N.s/kg. The specific impulse of the Orbiter was 310 lbf.s/lb.
         */

    }
}
