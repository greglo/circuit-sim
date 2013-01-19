package model.gates.basic;

import model.gates.AbstractGate;

public class OrGateFactory implements GateFactory {
    public static final OrGateFactory INSTANCE = new OrGateFactory();
    

    @Override
    public AbstractGate newGate() {
	return new Or(2);
    }

    @Override
    public AbstractGate newGate(int numInputs) {
	return new Or(numInputs);
    }

    private class Or extends AbstractGate {

	/**
	 * Construct n-input OR gate
	 * 
	 * @param numInputs
	 *            Number of inputs
	 */
	public Or(int numInputs) {
	    super(numInputs, 1);
	}

	/**
	 * Recalculate the output array from the inputs. Call upon changing any
	 * input
	 */
	@Override
	protected void computeOutput() {
	    int i = 0;
	    boolean anyTrue = false;

	    while (i < inputs.length && !anyTrue) {
		anyTrue = inputs[i];
		i++;
	    }

	    outputs[0] = anyTrue;
	}

	@Override
	public AbstractGate clone() {
	    Or newOr = new Or(inputs.length);
	    newOr.setInputs(inputs.clone());
	    return newOr;
	}

    }
}
