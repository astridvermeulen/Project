
package project.DB;

/**
 *
 * @author TEAM DB
 */
public class DBException extends Exception
{
    public DBException(String msg)
    {
        super(msg);
    }
    public DBException(Exception ex)
    {
        super(ex);
    }
}    

