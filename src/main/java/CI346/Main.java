package CI346;

import org.jscience.economics.money.Currency;
import org.jscience.economics.money.Money;
import org.jscience.physics.amount.Amount;

import javax.measure.quantity.Length;
import javax.measure.unit.UnitFormat;

import static javax.measure.unit.NonSI.*;
import static javax.measure.unit.SI.*;
import static org.jscience.economics.money.Currency.EUR;
import static org.jscience.economics.money.Currency.GBP;
import static org.jscience.economics.money.Currency.USD;


public class Main {

    public static void main(String[] args) {
        {
            System.out.println("");
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
            Amount<?> carMileage = Amount.valueOf(20, MILE
                    .divide(GALLON_LIQUID_US)); // 20 mi/gal.
            Amount<?> petrolPrice = Amount.valueOf(1.2, EUR.divide(LITRE)); // 1.2 €/L

            Amount<Length> tripDistance = Amount.valueOf(400, KILO(METRE)); // 400 km
            Amount<Money> tripCost = tripDistance.divide(carMileage).times(
                    petrolPrice).to(USD);
            // Displays cost.
            System.out.println("Trip cost = " + tripCost + " ("
                    + tripCost.to(EUR) + ")");

        }

        //Convert the NonSI unit lbf.s of impulse (pounds of force per second) to
        // its SI equivalent N.s (Newtons per second)
        {
            // (13913.994075448802 ± 2.7E-12) lbf·s/lb
            Amount<?> orbiterImpulse = Amount.valueOf("310 lbf*s/lb");
            //Amount<?> orbiterImpulse = Amount.valueOf(310, POUND_FORCE.times(SECOND).divide(POUND));
            System.out.println(orbiterImpulse.to(NEWTON.times(SECOND).divide(KILO(GRAM))));

            Amount<?> orbiterImpulse2 = Amount.valueOf(3041, NEWTON.times(SECOND).divide(KILO(GRAM)));
            //Amount<?> orbiterImpulse2 = Amount.valueOf("3041 N*s/kg");
            System.out.println(orbiterImpulse2.to(POUND_FORCE.times(SECOND).divide(POUND)));

            //Amount<?> orbiterThrust = Amount.valueOf(440, NEWTON);
            //System.out.println(orbiterThrust.to(KILOGRAM_FORCE));

        }
    }
}
