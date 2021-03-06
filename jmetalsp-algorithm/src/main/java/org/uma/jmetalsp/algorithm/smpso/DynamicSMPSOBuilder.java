package org.uma.jmetalsp.algorithm.smpso;

import org.uma.jmetal.operator.MutationOperator;
import org.uma.jmetal.solution.DoubleSolution;
import org.uma.jmetal.util.archive.BoundedArchive;
import org.uma.jmetal.util.evaluator.SolutionListEvaluator;
import org.uma.jmetal.util.evaluator.impl.SequentialSolutionListEvaluator;
import org.uma.jmetal.util.point.PointSolution;
import org.uma.jmetal.util.pseudorandom.JMetalRandom;
import org.uma.jmetal.util.pseudorandom.PseudoRandomGenerator;
import org.uma.jmetalsp.DynamicProblem;
import org.uma.jmetalsp.observeddata.AlgorithmObservedData;
import org.uma.jmetalsp.observer.Observable;
import org.uma.jmetalsp.qualityindicator.CoverageFront;

/**
 * @author Antonio J. Nebro <antonio@lcc.uma.es>
 */
public class DynamicSMPSOBuilder<
        P extends DynamicProblem<DoubleSolution, ?>> {

    protected MutationOperator<DoubleSolution> mutationOperator;
    private double c1Max;
    private double c1Min;
    private double c2Max;
    private double c2Min;
    private double r1Max;
    private double r1Min;
    private double r2Max;
    private double r2Min;
    private double weightMax;
    private double weightMin;
    private double changeVelocity1;
    private double changeVelocity2;
    private int swarmSize;
    private int maxIterations;
    private Observable<AlgorithmObservedData> observable;
    private BoundedArchive<DoubleSolution> leaders;

    private SolutionListEvaluator<DoubleSolution> evaluator;
    private boolean autoUpdate;
    private CoverageFront<PointSolution> coverageFront;

    public DynamicSMPSOBuilder(MutationOperator<DoubleSolution> mutationOperator,
                               BoundedArchive<DoubleSolution> leaders,
                               Observable<AlgorithmObservedData> observable, CoverageFront<PointSolution> coverageFront) {
        this.leaders = leaders;

        this.swarmSize = 100;
        this.maxIterations = 250;

        this.r1Max = 1.0;
        this.r1Min = 0.0;
        this.r2Max = 1.0;
        this.r2Min = 0.0;
        this.c1Max = 2.5;
        this.c1Min = 1.5;
        this.c2Max = 2.5;
        this.c2Min = 1.5;
        this.weightMax = 0.1;
        this.weightMin = 0.1;
        this.changeVelocity1 = -1;
        this.changeVelocity2 = -1;

        this.mutationOperator = mutationOperator;
        this.evaluator = new SequentialSolutionListEvaluator<DoubleSolution>();
        this.observable = observable;
        this.autoUpdate = false;
        this.coverageFront = coverageFront;
    }

    public DynamicSMPSOBuilder<P> setSwarmSize(int swarmSize) {
        this.swarmSize = swarmSize;

        return this;
    }

    public DynamicSMPSOBuilder<P> setMaxIterations(int maxIterations) {
        this.maxIterations = maxIterations;

        return this;
    }

    public DynamicSMPSOBuilder<P> setMutation(MutationOperator<DoubleSolution> mutation) {
        mutationOperator = mutation;

        return this;
    }

    public DynamicSMPSOBuilder<P> setC1Max(double c1Max) {
        this.c1Max = c1Max;

        return this;
    }

    public DynamicSMPSOBuilder<P> setC1Min(double c1Min) {
        this.c1Min = c1Min;

        return this;
    }

    public DynamicSMPSOBuilder<P> setC2Max(double c2Max) {
        this.c2Max = c2Max;

        return this;
    }

    public DynamicSMPSOBuilder<P> setC2Min(double c2Min) {
        this.c2Min = c2Min;

        return this;
    }

    public DynamicSMPSOBuilder<P> setR1Max(double r1Max) {
        this.r1Max = r1Max;

        return this;
    }

    public DynamicSMPSOBuilder<P> setR1Min(double r1Min) {
        this.r1Min = r1Min;

        return this;
    }

    public DynamicSMPSOBuilder<P> setR2Max(double r2Max) {
        this.r2Max = r2Max;

        return this;
    }

    public DynamicSMPSOBuilder<P> setR2Min(double r2Min) {
        this.r2Min = r2Min;

        return this;
    }

    public DynamicSMPSOBuilder<P> setWeightMax(double weightMax) {
        this.weightMax = weightMax;

        return this;
    }

    public DynamicSMPSOBuilder<P> setWeightMin(double weightMin) {
        this.weightMin = weightMin;

        return this;
    }

    public DynamicSMPSOBuilder<P> setChangeVelocity1(double changeVelocity1) {
        this.changeVelocity1 = changeVelocity1;

        return this;
    }

    public DynamicSMPSOBuilder<P> setChangeVelocity2(double changeVelocity2) {
        this.changeVelocity2 = changeVelocity2;

        return this;
    }

    public DynamicSMPSOBuilder<P> setRandomGenerator(PseudoRandomGenerator randomGenerator) {
        JMetalRandom.getInstance().setRandomGenerator(randomGenerator);

        return this;
    }

    public DynamicSMPSOBuilder<P> setSolutionListEvaluator(SolutionListEvaluator<DoubleSolution> evaluator) {
        this.evaluator = evaluator;

        return this;
    }

    public DynamicSMPSOBuilder<P> setAutoUpdate(boolean autoUpdate) {
        this.autoUpdate = autoUpdate;
        return this;
    }

    public DynamicSMPSO build(P problem) {
        return new DynamicSMPSO(problem, swarmSize, leaders, mutationOperator, maxIterations, r1Min, r1Max,
                r2Min, r2Max, c1Min, c1Max, c2Min, c2Max, weightMin, weightMax, changeVelocity1,
                changeVelocity2, evaluator, observable, autoUpdate, coverageFront);
    }
}
