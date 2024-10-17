package com.example.project;

public class LinearCalculator {
    // INSTANCE VARIABLES
    int x1, x2, y1, y2;

    // CONSTRUCTOR
    public LinearCalculator(String point1, String point2) {
        x1 = Integer.parseInt(point1.substring(1, point1.indexOf(","))); // Substring each num so that it starts at 1 (the index right after the left parenthesis) and stop at the comma capsulating the whole first coordinate regardless of length
        y1 = Integer.parseInt(point1.substring(point1.indexOf(",") + 1, point1.indexOf(")"))); // Do the same in the opposite direction, stop at the parentheses, encapsulating the number regardless of length
        x2 = Integer.parseInt(point2.substring(1, point2.indexOf(",")));
        y2 = Integer.parseInt(point2.substring(point2.indexOf(",") + 1, point2.indexOf(")")));
    }

    // GETTERS
    public int getX1() {return x1;}
    public int getY1() {return y1;}
    public int getX2() {return x2;}
    public int getY2() {return y2;}

    // SETTERS
    public void setX1(int x1) { this.x1 = x1; }
    public void setY1(int y1) { this.y1 = y1; }
    public void setX2(int x2) { this.x2 = x2; }
    public void setY2(int y2) { this.y2 = y2; }

    // METHOD TO CALCULATE DISTANCE
    public double distance() {
        double hyp = Math.sqrt(Math.pow((x2 - x1), 2) + Math.pow((y2 - y1), 2)); // Pythagorean Theorem = sqrt(a^2 + b^2)
        return roundedToHundredth(hyp);
    }

    // METHOD TO CALCULATE SLOPE
    public double slope() {
        if (x2 - x1 == 0) {
            return -999.99;  // Slope undefined
        }
        double slope = (double) (y2 - y1) / (x2 - x1); 
        return roundedToHundredth(slope);
    }

    // METHOD TO CALCULATE Y-INTERCEPT
    public double yInt() {
        double slope = slope();
        if (slope == -999.99) {
            return -999.99;  // Undefined y-intercept
        }
        double yIntercept = y1 - slope * x1;
        return roundedToHundredth(yIntercept);
    }

    // METHOD TO RETURN THE EQUATION
    
    public String equation() {
        double m = slope();  // Calculate the slope
        double b = yInt();   // Calculate the y-intercept
    
        // If the slope is undefined, return "undefined"
        if (m == -999.99) {
            return "undefined";
        }
    
        // Start building the equation string
        StringBuilder equation = new StringBuilder("y=");
    
        // Only add the slope and "x" if the slope isn't 0.0
        if (m != 0.0) {
            equation.append(m == 1 ? "" : (m == -1 ? "-" : m)).append("x");
        }
    
        // Add the y-intercept only if it's not 0.0
        if (b != 0.0) {
            if (m != 0.0) {
                equation.append(b > 0 ? "+" : "-").append(Math.abs(b));
            } else {
                equation.append(b);  // If slope is 0, only add the y-intercept directly
            }
        } else if (m == 0.0) {
            // If both slope and intercept are 0.0, the equation is y = 0
            equation.append(0.0);
        }
    
        return equation.toString();
    }
    
    // METHOD TO ROUND TO THE NEAREST HUNDREDTH
    public double roundedToHundredth(double x) {
        return Math.round(x * 100.0) / 100.0;
    }

    // METHOD TO PRINT INFO
    public String printInfo() {
        String str = "The two points are: (" + x1 + "," + y1 + ")";
        str += " and (" + x2 + "," + y2 + ")";
        str += "\nThe equation of the line between these points is: " + equation();
        str += "\nThe slope of this line is: " + slope();
        str += "\nThe y-intercept of the line is: " + yInt();
        str += "\nThe distance between the two points is: " + distance();
        return str;
    }
}
