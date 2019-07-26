package beans.application;

import db.entities.Account;
import db.entities.Record;
import javax.ejb.EJB;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.Initialized;
import javax.enterprise.event.Observes;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import db.*;

@ApplicationScoped
public class DatabaseInitializer {
    @EJB
    private AccountsDb adb;
    @EJB
    private RecordsDb rdb;
    
    // テスト用DB 初期化処理
    private void init(@Observes @Initialized(ApplicationScoped.class) Object event) {
//        initAccounts();
//        initRecords();
    }
    
    private void initAccounts() {
        try {
            adb.create(new Account("username", "password", "あいうえおじ"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    private void initRecords() {
        try {
            DateFormat df = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
            rdb.create(new Record(1, df.parse("2019/06/01 08:00:00"), 79, 115, 88, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/02 08:00:00"), 82, 122, 90, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/03 08:00:00"), 81, 119, 90, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/04 08:00:00"), 85, 124, 91, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/05 08:00:00"), 79, 119, 89, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/06 08:00:00"), 78, 120, 90, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/07 08:00:00"), 79, 125, 92, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/08 08:00:00"), 74, 112, 87, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/09 08:00:00"), 82, 120, 88, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/10 08:00:00"), 78, 118, 89, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/11 08:00:00"), 84, 119, 91, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/12 08:00:00"), 82, 121, 88, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/13 08:00:00"), 80, 116, 85, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/14 08:00:00"), 77, 112, 84, "起床時"));
            rdb.create(new Record(1, df.parse("2019/06/15 08:00:00"), 82, 122, 89, "起床時"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
