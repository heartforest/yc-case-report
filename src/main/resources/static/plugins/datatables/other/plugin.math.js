/**
 * Fairly simply, this plug-in will take the data from an API result set
 * and sum it, returning the summed value. The data can come from any data
 * source, including column data, cells or rows.
 *
 * Note that it will attempt to 'deformat' any string based data that is passed
 * into it - i.e. it will strip any non-numeric characters in order to make a
 * best effort attempt to sum all data types. This can be useful when working
 * with formatting numbers such as currency. However the trade-off is that no
 * error is thrown if non-numeric data is passed in. You should be aware of this
 * in case unexpected values are returned - likely the input data is not what is
 * expected.
 *
 * @name sum()
 * @summary Sum the values in a data set.
 * @author [Allan Jardine](http://sprymedia.co.uk)
 * @requires DataTables 1.10+
 *
 * @returns {Number} Summed value
 *
 * @example
 * // Simply get the sum of a column
 * var table = $('#example').DataTable();
 * table.column( 3 ).data().sum();
 *
 * @example
 * // Insert the sum of a column into the columns footer, for the visible
 * // data on each draw
 * $('#example').DataTable( { 
 * drawCallback: function () { 
 * var api = this.api(); 
 * $( api.table().footer() ).html( 
 * api.column( 4, {page:'current'} ).data().sum() 
 * ); 
 * } 
 * } );
 */
jQuery.fn.dataTable.Api.register('sum()', function () {
    return this.flatten().reduce(function (a, b) {
        try {
            //hforest add
            a = typeof a === "string" ? a.replace(/[^\d.-]/g, '') * 1 : typeof a === "number" ? a : 0;
            b = typeof b === "string" ? b.replace(/[^\d.-]/g, '') * 1 : typeof b === "number" ? b : 0;
            /*
             if (typeof a === 'string') {
             a = a.replace(/[^\d.-]/g, '') * 1;
             }
             if (typeof b === 'string') {
             b = b.replace(/[^\d.-]/g, '') * 1;
             }
             */
            //console.log(a + "<=====[+]=====>" + b);
            return a + b;
        } catch (e) {
            return 0;
        }
    }, 0);
});
/**
 * It can sometimes be useful to get the average of data in an API result set,
 * be it from a column, or a collection of cells. This method provides exactly
 * that ability.
 *
 *  @name average()
 *  @summary Average the values in a data set.
 *  @author [Allan Jardine](http://sprymedia.co.uk)
 *  @requires DataTables 1.10+
 *
 * @returns {Number} Calculated average
 *
 *  @example
 *    // Average a column
 *    var table = $('#example').DataTable();
 *    table.column( 3 ).data().average();
 *
 *  @example
 *    // Average two cells
 *    var table = $('#example').DataTable();
 *    table.cells( 0, [3,4] ).data().average();
 */
jQuery.fn.dataTable.Api.register('average()', function () {
    try {
        var data = this.flatten();
        var sum = data.reduce(function (a, b) {
            //hforest add
            a = typeof a === "string" ? a.replace(/[^\d.-]/g, '') * 1 : typeof a === "number" ? a : 0;
            b = typeof b === "string" ? b.replace(/[^\d.-]/g, '') * 1 : typeof b === "number" ? b : 0;
            return (a * 1) + (b * 1); // cast values in-case they are strings
        });
    } catch (e) {
        return 0;
    }
    return sum / data.length;
});

