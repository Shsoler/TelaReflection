package annotations;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import enume.TipoEntrada;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.TYPE})
public @interface Tela {
	int alturatela() default 400;
	int larguratela() default 400;


}
