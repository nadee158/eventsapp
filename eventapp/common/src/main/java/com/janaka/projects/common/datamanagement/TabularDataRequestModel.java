package com.janaka.projects.common.datamanagement;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.jpa.datatables.mapping.DataTablesInput;

/**
 * {@link http://datatables.net/manual/server-side}
 */
public class TabularDataRequestModel extends DataTablesInput implements Serializable {

  private static final long serialVersionUID = 1L;

  private Map<String, Object> customData = new HashMap<String, Object>();

  public Map<String, Object> getCustomData() {
    return customData;
  }



  // // draw integer Draw counter. This is used by DataTables to ensure that the Ajax returns from
  // // server-side processing requests are drawn in sequence by DataTables (Ajax requests are
  // // asynchronous and thus can return out of sequence). This is used as part of the draw return
  // // parameter (see below).
  // // start integer Paging first record indicator. This is the start point in the current data set
  // (0
  // // index based - i.e. 0 is the first record).
  // // length integer Number of records that the table can display in the current draw. It is
  // expected
  // // that the number of records returned will be equal to this number, unless the server has
  // fewer
  // // records to return. Note that this can be -1 to indicate that all records should be returned
  // // (although that negates any benefits of server-side processing!)
  // // Global search value. To be applied to all columns which have searchable as true.
  // // true if the global filter should be treated as a regular expression for advanced searching,
  // // false otherwise. Note that normally server-side processing scripts will not perform regular
  // // expression searching for performance reasons on large data sets, but it is technically
  // possible
  // // and at the discretion of your script.
  // // Column to which ordering should be applied. This is an index reference to the columns array
  // of
  // // information that is also submitted to the server.
  // // Ordering direction for this column. It will be asc or desc to indicate ascending ordering or
  // // descending ordering, respectively.
  // // Column's data source, as defined by columns.data
  // // Column's name, as defined by columns.name.
  // // Flag to indicate if this column is searchable (true) or not (false). This is controlled by
  // // columns.searchable.
  // // Flag to indicate if this column is orderable (true) or not (false). This is controlled by
  // // columns.orderable.
  // // Search value to apply to this specific column.
  // // Flag to indicate if the search term for this column should be treated as regular expression
  // // (true) or not (false). As with global search, normally server-side processing scripts will
  // not
  // // perform regular expression searching for performance reasons on large data sets, but it is
  // // technically possible and at the discretion of your script.
  //
  //
  //
  // private int draw;
  // private int start;
  // private int length;
  // private Search search;
  // private List<Order> order;
  // private List<Column> columns;
  //
  // public int getDraw() {
  // return draw;
  // }
  //
  // public void setDraw(int draw) {
  // this.draw = draw;
  // }
  //
  // public int getStart() {
  // return start;
  // }
  //
  // public void setStart(int start) {
  // this.start = start;
  // }
  //
  // public int getLength() {
  // return length;
  // }
  //
  // public void setLength(int length) {
  // this.length = length;
  // }
  //
  // public Search getSearch() {
  // return search;
  // }
  //
  // public void setSearch(Search search) {
  // this.search = search;
  // }
  //
  // public List<Order> getOrder() {
  // return order;
  // }
  //
  // public void setOrder(List<Order> order) {
  // this.order = order;
  // }
  //
  // public List<Column> getColumns() {
  // return columns;
  // }
  //
  // public void setColumns(List<Column> columns) {
  // this.columns = columns;
  // }
  //
  // @Override
  // public String toString() {
  // return "TabularDataRequestModel [draw=" + draw + ", start=" + start + ", length=" + length +
  // "]";
  // }



}
