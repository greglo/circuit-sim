package model.gates.basic;

import model.gates.AbstractGate;

public class AndGateFactory implements GateFactory {
    public static final AndGateFactory INSTANCE = new AndGateFactory();
    

    @Override
    public AbstractGate newGate() {
	return new And(2);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	return new And(numInputs);
    }

    private class And extends AbstractGate {

	/**
	 * Construct n-input AND gate
	 * 
	 * @param numInputs
	 *            Number of inputs
	 */
	public And(int numInputs) {
	    super(numInputs, 1);
	}

	/**
	 * Recalculate the output array from the inputs. Call upon changing any
	 * input
	 */
	@Override
	protected void computeOutput() {
	    int i = 0;
	    boolean allTrue = true;

	    while (i < inputs.length && allTrue) {
		allTrue = inputs[i];
		i++;
	    }

	    outputs[0] = allTrue;
	}

	@Override
	public AbstractGate clone() {
	    And newAnd = new And(inputs.length);
	    newAnd.setInputs(inputs.clone());
	    return newAnd;
	}

    }
}
