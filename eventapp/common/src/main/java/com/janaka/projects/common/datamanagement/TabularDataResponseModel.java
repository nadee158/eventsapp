package com.janaka.projects.common.datamanagement;

import java.io.Serializable;
import java.util.List;

import org.springframework.data.jpa.datatables.mapping.DataTablesOutput;


/**
 * {@link http://datatables.net/manual/server-side}
 */
public class TabularDataResponseModel<E> extends DataTablesOutput<E> implements Serializable {

  private static final long serialVersionUID = 1L;

  // draw integer The draw counter that this object is a response to - from the draw parameter
  // sent
  // as part of the data request. Note that it is strongly recommended for security reasons that
  // you
  // cast this parameter to an integer, rather than simply echoing back to the client what it
  // sent
  // in the draw parameter, in order to prevent Cross Site Scripting (XSS) attacks.
  // recordsTotal integer Total records, before filtering (i.e. the total number of records in
  // the
  // database)
  // recordsFiltered integer Total records, after filtering (i.e. the total number of records
  // after
  // filtering has been applied - not just the number of records being returned for this page of
  // data).
  // data array The data to be displayed in the table. This is an array of data source objects,
  // one
  // for each row, which will be used by DataTables. Note that this parameter's name can be
  // changed
  // using the ajax option's dataSrc property.
  // error string Optional: If an error occurs during the running of the server-side processing
  // script, you can inform the user of this error by passing back the error message to be
  // displayed
  // using this parameter. Do not include if there is no error.


  private boolean isSuccessful = false;
  // private int draw;
  // private long recordsTotal;
  // private long recordsFiltered;
  // private List<E> data = new ArrayList<E>();
  // private String error = StringUtils.EMPTY;

  public TabularDataResponseModel(TabularDataRequestModel request, long recordsTotal, long recordsFiltered,
      List<E> data) {
    this(request.getDraw(), recordsTotal, recordsFiltered, data);
  }

  private TabularDataResponseModel(int draw, long recordsTotal, long recordsFiltered, List<E> data) {
    super();
    setData(data);
    setDraw(draw);
    setRecordsTotal(recordsTotal);
    setRecordsFiltered(recordsFiltered);
    this.isSuccessful = true;
  }

  public TabularDataResponseModel(TabularDataRequestModel request, String error) {
    super();

    if (error == null) {
      throw new RuntimeException();
    }
    setDraw(request.getDraw());
    setError(error);
    this.isSuccessful = false;
  }



  public TabularDataResponseModel() {
    super();
  }

  public boolean isSuccessful() {
    return this.isSuccessful;
  }
  //
  // public int getDraw() {
  // return draw;
  // }
  //
  //
  // public long getRecordsTotal() {
  // return recordsTotal;
  // }
  //
  // public void setRecordsTotal(long recordsTotal) {
  // this.recordsTotal = recordsTotal;
  // }
  //
  // public long getRecordsFiltered() {
  // return recordsFiltered;
  // }
  //
  // public void setRecordsFiltered(long recordsFiltered) {
  // this.recordsFiltered = recordsFiltered;
  // }
  //
  // public String getError() {
  // return error;
  // }
  //
  // public List<E> getData() {
  // return data;
  // }

}
