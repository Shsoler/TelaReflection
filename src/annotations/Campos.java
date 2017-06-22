package annotations;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import enume.TipoEntrada;

@Retention(RUNTIME)
@Target(FIELD)
public @interface Campos {
	String nomeCampo() default "";
	TipoEntrada tipoEntrada() default TipoEntrada.TEXTFIELD;
	int altura() default 20;
	int largura() default 100;
	boolean ativo() default true;
	String[] opcoesSelecao() default {};
}
