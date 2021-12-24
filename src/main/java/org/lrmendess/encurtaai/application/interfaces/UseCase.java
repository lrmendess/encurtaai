package org.lrmendess.encurtaai.application.interfaces;

public interface UseCase<I, O> {

    O handle(I input);

}
