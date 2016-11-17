# dimensions

Developers often need to model units of measure, because objects in the real
world are subject to these measures. We could use plain numeric types but how 
then would we know whether this method expects grams, kilograms or something 
else, and what type of money value it returns?

    int costOfApples(int weight)

The need for *dimension types* is explained by A.J. Kennedy as follows [1] 

"Dimensions are to science what types are to programming. In science and engineering, 
dimensional consistency provides a first check on the correctness of
an equation or formula, just as in programming the typability of a program or
program fragment eliminates one possible reason for program failure.
What then of dimensions in programming? After all, the writing of a scientific
program involves turning a set of equations which model some physical situation
into a set of instructions making up a computer program. It still makes sense
to talk about the dimensions of a formula, even when represented as a program
fragment written in some programming language."

A specification for dimension types in Java has been accepted [2], so these will
be available in a future version of the language. We can use them already, using 
<<<<<<< HEAD
the "reference implementation" from JScience.org, as this lab exercise does.
=======
the "reference implementation" from JScience.org, as well as the `javax.measure` API.
>>>>>>> master

Clone the repository with the following command:

    $ git clone https://github.com/jimburton/dimensions.git

<<<<<<< HEAD
It uses maven to download the `unitsofmeasurement` API. So if you're using Eclipse,
import it as a maven project. IntelliJ can open maven projects without any bother.
Maven downloads the JScience library for you but you want the sources as well, so 
that you can read the docs. In Eclipse, you can tell maven to do this in 
*Window / Preferences / Maven*. In ItelliJ, open the Maven Projects tool window,
find the button that lets you execute a maven goal and execute these goals:

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc

## Do you know the way to San Jose?

The `Main` class contains an example of using the `unitsofmeasurement` API. It
calculates the fuel cost of a 400 mile journey for a US tourist in Europe. Use
the same ideas to calculate the cost of a journey from New York to San Jose
(google the distance) and convert it to Pounds Sterling for your British users.

## It's not Rocket Science

You'll have heard of the Mars Climate Orbiter disaster [3], which was caused by a 
dimension conversion error dimension. Programmers at NASA treated a SI (or metric) 
value as an Imperial measure (miles, tons and so on, still used in the US). The
data that caused the error was the measure of *impulse*, which is a measure of 
`force . time / weight`. The Imperial measure of force is `lbf` ("pounds of force")
so NASA was measuring impulse as `lbf.s/ton`. The SI unit of force is the `Newton`,
(`N`) so the European programmers were expecting impulse to be in units of `N.s/kg`.



Start by creating an `Amount` with the right dimensions to represent the Imperial value.
There are two ways to do this. The library can parse strings that represent values,
so you could do this:

    Amount<?> orbiterImpulse = Amount.valueOf("310 lbf*s/lb"); 
    
There is a more flexible way though, using the version of `valueOf` that takes a numeric 
value (either a `long` or a `double`) and the `Unit` it is measured in. Non-SI (e.g. Imperial)
units are defined here, while SI units are defined here. The `Unit` class comes with 
methods to make compound units. For instance, accelleration is measured in `m / s^2`, which 
we can create a `Unit` for: 

    Unit<?> acc = METRE.divide(SECOND.pow(2));
    
Then we can make a `Value` representing acceleration at gravitational acceleration on Earth:
    
    Amount<?> g = Amount.valueOf(9.78, acc); 

The impulse of the orbiter as it approached Mars was apparently about `310 lbf.s/ton`.
Use the `unitsofmeasurement` API to convert this value into `N.s/kg`, saving $327 
million and a lot of red faces.

[1] https://www.cl.cam.ac.uk/techreports/UCAM-CL-TR-391.pdf
[2] JSR 363: https://github.com/unitsofmeasurement/unit-api
=======
This tiny project uses maven to download the `unitsofmeasurement` API. So if you're using Eclipse
you need the maven plugin, which will allow you to import the repository as a maven project. 
IntelliJ can open maven projects without any bother. Maven downloads the JScience library for you 
but you want the sources as well, so that you can read the docs. In Eclipse, you can tell maven to do this in 
*Window / Preferences / Maven*. In ItelliJ, open the Maven Projects tool window, find the button that lets 
you execute a maven goal and execute these goals:

    mvn dependency:sources
    mvn dependency:resolve -Dclassifier=javadoc
    

The first class you need to understand is `Amount`. Read the docs for it 
in your IDE or [here](http://jscience.org/api/org/jscience/physics/amount/package-summary.html).
Each amount has particular unit -- SI units (e.g. metres and kolograms) are defined 
[here](http://jscience.org/api/javax/measure/unit/SI.html), while non-SI units (including pounds, feet, etc) 
are defined [here](http://jscience.org/api/javax/measure/unit/NonSI.html). The `Main` class contains 
an example of using the API taken from the docs. It calculates the fuel cost of a 400 mile journey for 
a US tourist in Europe. Read `Main.java` and run it to get an example of how units and amounts are used
and how to convert an amount from one unit to another.

## Do you know the way to San Jose?

Add code to `Main.java` to calculate the fuel cost of a car journey from New York to San Jose
(google the distance by road), convert it to Pounds Sterling for your British users and print 
out the result.

## It's not Rocket Science

You'll probably have heard of the Mars Climate Orbiter disaster [3], which was caused by a 
dimension conversion error. Programmers at NASA treated a SI value coming from code written
in Europe as a US measure (miles, tons and so on are still used in the US). The data that 
caused the error was the measure of *impulse*, which is a measure of *force* multiplied by *time*. Rather 
than calculating the impulse of the whole spaceship, it's more useful to know the *specific impulse*, 
which is the *impulse per unit of fuel*. The Imperial measure of force is `lbf` ("pounds of force") so
NASA was expecting the specific impulse to be in `lbf.s/lb` ("(pounds of force times seconds) divided by pounds"). 
The SI unit of force is the `Newton`, (`N`) so the European programmers code the 
specific impulse in units of `N.s/kg`.

Start by creating an `Amount` with the right dimensions to represent the SI value.
There are two ways to do this. The library can parse strings that represent values,
so you could just do this:

    Amount<?> orbiterImpulse = Amount.valueOf("3041 lbf*s/lb"); 
    
There is a more flexible way though, using the version of `Amount.valueOf` that takes a numeric 
value (either a `long` or a `double`) and the `Unit` it is measured in; this is the approach 
taken in the code provided for car journeys. In that code we made a new unit by combining `MILE`
and `GALLON_LIQUID_US`. This gave us a unit representing *miles per gallon*: 
    
    Unit<?> mpg = MILE.divide(GALLON_LIQUID_US); 

Then we create a specific amount of 20 mpg with the `valueOf` method:
    
    Amount<?> carMileage = Amount.valueOf(20, mpg); // 20 miles per gallon.
        
The Mars Orbiter had a specific impulse of `3041 N.s/kg`. Convert this 
into a value using the US unit `lbf.s/lb`, saving $327 million and a lot of red faces.

[1] https://www.cl.cam.ac.uk/techreports/UCAM-CL-TR-391.pdf

[2] JSR 363: https://github.com/unitsofmeasurement/unit-api

>>>>>>> master
[3] https://en.wikipedia.org/wiki/Mars_Climate_Orbiter
