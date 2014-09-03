package br.util;

//import java.io.PrintWriter;
//import java.io.StringWriter;
import java.util.Iterator;
import java.util.Map;
import javax.faces.FacesException;
import javax.faces.application.FacesMessage;
import javax.faces.application.NavigationHandler;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;

//Inicialmente devemos implementar a classe CustomExceptionHandler que extende a classe ExceptionHandlerWrapper
public class CustomExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;

	// Obt�m uma inst�ncia do FacesContext
	final FacesContext facesContext = FacesContext.getCurrentInstance();

	// Obt�m um mapa do FacesContext
	final Map requestMap = facesContext.getExternalContext().getRequestMap();

	// Obt�m o estado atual da navega��o entre p�ginas do JSF
	final NavigationHandler navigationHandler = facesContext.getApplication()
			.getNavigationHandler();

	// Declara o construtor que recebe uma exceptio do tipo ExceptionHandler
	// como par�metro
	CustomExceptionHandler(ExceptionHandler exception) {
		this.wrapped = exception;
	}

	// Sobrescreve o m�todo ExceptionHandler que retorna a "pilha" de exce��es
	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	// Sobrescreve o m�todo handle que � respons�vel por manipular as exce��es
	// do JSF
	@Override
	public void handle() throws FacesException {

		final Iterator iterator = getUnhandledExceptionQueuedEvents()
				.iterator();
		while (iterator.hasNext()) {
			ExceptionQueuedEvent event = (ExceptionQueuedEvent) iterator.next();
			ExceptionQueuedEventContext context = (ExceptionQueuedEventContext) event
					.getSource();

			// Recupera a exce��o do contexto
			Throwable exception = context.getException();

			// Aqui tentamos tratar a exe��o
			try {

				// // Aqui voc� poderia por exemploinstanciar as classes
				// StringWriter e PrintWriter
				// StringWriter stringWriter = new StringWriter();
				// // PrintWriter printWriter = new PrintWriter(stringWriter);
				// // exception.printStackTrace(printWriter);
				// // Por fim voc� pode converter a pilha de exce��es em uma
				// String
				// String message = stringWriter.toString();
				//
				// // Aqui voc� poderia enviar um email com a StackTrace
				// // em anexo para a equipe de desenvolvimento
				//
				// // e depois imprimir a stacktrace no log
				// exception.printStackTrace();

				// Coloca uma mensagem de exce��o no mapa da request
				requestMap.put("exceptionMessage", exception.getMessage());

				// Avisa o usu�rio do erro
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_ERROR,
										"O sistema se recuperou de um erro inesperado.",
										""));

				// Tranquiliza o usu�rio para que ele continue usando o sistema
				FacesContext
						.getCurrentInstance()
						.addMessage(
								null,
								new FacesMessage(
										FacesMessage.SEVERITY_INFO,
										"Voc� pode continuar usando o sistema normalmente!",
										""));

				// Seta a navega��o para uma p�gina padr�o.
				navigationHandler.handleNavigation(facesContext, null,
						"/restrict/home.faces");

				// Renderiza a pagina de erro e exibe as mensagens
				facesContext.renderResponse();
			} finally {
				// Remove a exe��o da fila
				iterator.remove();
			}
		}
		// Manipula o erro
		getWrapped().handle();
	}
}