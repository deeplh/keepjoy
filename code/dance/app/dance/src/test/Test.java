package test;

import jsontag.annotation.JsonTagAnnotation;
import jsontag.interf.IDoAction;

@JsonTagAnnotation(actionValue = "test",namespace = "test")
public class Test implements IDoAction {
    @Override
    public Object doAction() throws Exception {
        return 123;
    }
}
