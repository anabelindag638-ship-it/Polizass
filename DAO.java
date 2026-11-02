package polizas.General;

import java.util.List;

public abstract interface DAO {
    int create(Object obj);
    List read();
    int update(Object lista);
    int delete(Object obj);
}