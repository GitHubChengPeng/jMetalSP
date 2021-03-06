package org.uma.jmetalsp.problem.df;

import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.JMetalException;
import org.uma.jmetalsp.observeddata.ObservedValue;
import org.uma.jmetalsp.observer.Observable;
import org.uma.jmetalsp.observer.impl.DefaultObservable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DF6 extends DF implements Serializable {
    public DF6(Observable<ObservedValue<Integer>> observable){
        this(10,2, observable);
    }

    public DF6() {
        this(new DefaultObservable<>()) ;
    }

    public DF6(Integer numberOfVariables, Integer numberOfObjectives, Observable<ObservedValue<Integer>> observer) throws JMetalException {
        super(observer) ;
        setNumberOfVariables(numberOfVariables);
        setNumberOfObjectives(numberOfObjectives);
        setName("DF6");

        List<Double> lowerLimit = new ArrayList<>(getNumberOfVariables());
        List<Double> upperLimit = new ArrayList<>(getNumberOfVariables());

        lowerLimit.add(0.0);
        upperLimit.add(1.0);
        for (int i = 1; i < getNumberOfVariables(); i++) {
            lowerLimit.add(-1.0);
            upperLimit.add(1.0);
        }

        setLowerLimit(lowerLimit);
        setUpperLimit(upperLimit);
        time=0.0d;
        theProblemHasBeenModified=false;
    }
    @Override
    public boolean hasTheProblemBeenModified() {
        return theProblemHasBeenModified;
    }

    @Override
    public void reset() {
        theProblemHasBeenModified = false ;
    }

    @Override
    public void evaluate(DoubleSolution solution) {
        double[] f = new double[getNumberOfObjectives()];
        double G = Math.sin(0.5*Math.PI*time);
        double a = 0.2d+2.8d*Math.abs(G);
        double g = 1.0d+evaluateG(solution,G);

        f[0] = g*Math.pow(solution.getVariableValue(0)+0.1d*Math.sin(3*Math.PI*solution.getVariableValue(0)),a);
        f[1] = g*Math.pow(1.0-solution.getVariableValue(0)+0.1d*Math.sin(3*Math.PI*solution.getVariableValue(0)),a);

        solution.setObjective(0, f[0]);
        solution.setObjective(1, f[1]);
    }
    private double evaluateG(DoubleSolution solution, double G){
        double result=0.0;
        for (int i=1;i<solution.getNumberOfVariables();i++){
            double y = solution.getVariableValue(i)-G;
            result+= Math.abs(G)*Math.pow(y,2.0d)-10.0d*Math.cos(2.0d*Math.PI*y)+10.0d;
        }
        return result;
    }

}
