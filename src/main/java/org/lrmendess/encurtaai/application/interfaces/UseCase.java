package org.lrmendess.encurtaai.application.interfaces;

public interface UseCase<I, O> {

    public O handle(I input);

}
