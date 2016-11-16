package com.janaka.projects.services.web.rest;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.servlet.mvc.multiaction.NoSuchRequestHandlingMethodException;

@ControllerAdvice
public class CustomRestExceptionHandler extends ResponseEntityExceptionHandler {

  // 400
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(final MethodArgumentNotValidException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleMethodArgumentNotValid### ", ex);
    System.out.println("handleMethodArgumentNotValid### " + ex);
    //
    final List<String> errors = new ArrayList<String>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleBindException(final BindException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleBindException### ", ex);
    System.out.println("handleBindException### " + ex);
    //
    final List<String> errors = new ArrayList<String>();
    for (final FieldError error : ex.getBindingResult().getFieldErrors()) {
      errors.add(error.getField() + ": " + error.getDefaultMessage());
    }
    for (final ObjectError error : ex.getBindingResult().getGlobalErrors()) {
      errors.add(error.getObjectName() + ": " + error.getDefaultMessage());
    }
    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleTypeMismatch(final TypeMismatchException ex, final HttpHeaders headers,
      final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleTypeMismatch### ", ex);
    System.out.println("handleTypeMismatch### " + ex);
    //
    final String error =
        ex.getValue() + " value for " + ex.getPropertyName() + " should be of type " + ex.getRequiredType();

    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestPart(final MissingServletRequestPartException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleMissingServletRequestPart### ", ex);
    System.out.println("handleMissingServletRequestPart### " + ex);
    //
    final String error = ex.getRequestPartName() + " part is missing";
    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingServletRequestParameter(
      final MissingServletRequestParameterException ex, final HttpHeaders headers, final HttpStatus status,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleMissingServletRequestParameter### ", ex);
    System.out.println("handleMissingServletRequestParameter### " + ex);
    //
    final String error = ex.getParameterName() + " parameter is missing";
    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  // 404
  @Override
  protected ResponseEntity<Object> handleNoHandlerFoundException(final NoHandlerFoundException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleNoHandlerFoundException### ", ex);
    System.out.println("handleNoHandlerFoundException### " + ex);
    //
    final String error = "No handler found for " + ex.getHttpMethod() + " " + ex.getRequestURL();

    final ApiError apiError = new ApiError(HttpStatus.NOT_FOUND, ex.getLocalizedMessage(), error);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  // 405
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(final HttpRequestMethodNotSupportedException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleHttpRequestMethodNotSupported### ", ex);
    System.out.println("handleHttpRequestMethodNotSupported### " + ex);
    //
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getMethod());
    builder.append(" method is not supported for this request. Supported methods are ");
    ex.getSupportedHttpMethods().forEach(t -> builder.append(t + " "));

    final ApiError apiError = new ApiError(HttpStatus.METHOD_NOT_ALLOWED, ex.getLocalizedMessage(), builder.toString());
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, headers, apiError.getStatus(), request);
  }

  // 415
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(final HttpMediaTypeNotSupportedException ex,
      final HttpHeaders headers, final HttpStatus status, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleHttpMediaTypeNotSupported### ", ex);
    System.out.println("handleHttpMediaTypeNotSupported### " + ex);
    //
    final StringBuilder builder = new StringBuilder();
    builder.append(ex.getContentType());
    builder.append(" media type is not supported. Supported media types are ");
    ex.getSupportedMediaTypes().forEach(t -> builder.append(t + " "));

    final ApiError apiError = new ApiError(HttpStatus.UNSUPPORTED_MEDIA_TYPE, ex.getLocalizedMessage(),
        builder.substring(0, builder.length() - 2));
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler({MethodArgumentTypeMismatchException.class})
  public ResponseEntity<Object> handleMethodArgumentTypeMismatch(final MethodArgumentTypeMismatchException ex,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleMethodArgumentTypeMismatch### ", ex);
    System.out.println("handleMethodArgumentTypeMismatch### " + ex);
    //
    final String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();

    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), error);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler({ConstraintViolationException.class})
  public ResponseEntity<Object> handleConstraintViolation(final ConstraintViolationException ex,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleConstraintViolation### ", ex);
    System.out.println("handleConstraintViolation### " + ex);
    //
    final List<String> errors = new ArrayList<String>();
    for (final ConstraintViolation<?> violation : ex.getConstraintViolations()) {
      errors.add(
          violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage());
    }

    final ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, ex.getLocalizedMessage(), errors);
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler({DataIntegrityViolationException.class})
  public ResponseEntity<Object> handleConflictException(final DataIntegrityViolationException ex,
      final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleConflictException### ", ex);
    System.out.println("handleConflictException### " + ex);
    final ApiError apiError = new ApiError(HttpStatus.CONFLICT, ex.getLocalizedMessage(), ex.getMessage());
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  // 403
  @ExceptionHandler({AccessDeniedException.class})
  public ResponseEntity<Object> handleAccessDenied(final AccessDeniedException ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleAccessDenied### ", ex);
    System.out.println("handleAccessDenied### " + ex);
    //
    final ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, ex.getLocalizedMessage(), "Forbidden");
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  @ExceptionHandler({BadCredentialsException.class})
  public ResponseEntity<Object> handleBadCredentials(final BadCredentialsException ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleBindException### ", ex);
    System.out.println("handleBindException### " + ex);
    //
    final ApiError apiError = new ApiError(HttpStatus.UNAUTHORIZED, ex.getLocalizedMessage(), "Forbidden");
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }

  // 500
  @ExceptionHandler({Exception.class})
  public ResponseEntity<Object> handleGeneralException(final Exception ex, final WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleGeneralException### ", ex);
    System.out.println("handleGeneralException### " + ex);
    //
    final ApiError apiError = new ApiError(HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage(), ex.getMessage());
    // return new ResponseEntity<Object>(apiError, new HttpHeaders(), apiError.getStatus());
    return handleExceptionInternal(ex, apiError, new HttpHeaders(), apiError.getStatus(), request);
  }


  @Override
  protected ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleConversionNotSupported### ", ex);
    System.out.println("handleConversionNotSupported### " + ex);
    return super.handleConversionNotSupported(ex, headers, status, request);
  }


  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleHttpMediaTypeNotAcceptable### ", ex);
    System.out.println("handleHttpMediaTypeNotAcceptable### " + ex);
    return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
  }


  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleHttpMessageNotReadable### ", ex);
    System.out.println("handleHttpMessageNotReadable### " + ex);
    return super.handleHttpMessageNotReadable(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleHttpMessageNotWritable### ", ex);
    System.out.println("handleHttpMessageNotWritable### " + ex);
    return super.handleHttpMessageNotWritable(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers,
      HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleMissingPathVariable### ", ex);
    System.out.println("handleMissingPathVariable### " + ex);
    return super.handleMissingPathVariable(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleNoSuchRequestHandlingMethod(NoSuchRequestHandlingMethodException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleNoSuchRequestHandlingMethod### ", ex);
    System.out.println("handleNoSuchRequestHandlingMethod### " + ex);
    return super.handleNoSuchRequestHandlingMethod(ex, headers, status, request);
  }

  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(ServletRequestBindingException ex,
      HttpHeaders headers, HttpStatus status, WebRequest request) {
    logger.info(ex.getClass().getName());
    logger.error("error", ex);
    logger.warn("handleServletRequestBindingException### ", ex);
    System.out.println("handleServletRequestBindingException### " + ex);
    return super.handleServletRequestBindingException(ex, headers, status, request);
  }

}
