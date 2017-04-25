package com.example.dell.inpods;
import java.util.ArrayList;
		import java.util.List;
		import android.content.ContentValues;
		import android.content.Context;
		import android.database.Cursor;
		import android.database.sqlite.SQLiteDatabase;
		import android.database.sqlite.SQLiteOpenHelper;
		import com.example.dell.inpods.Question;

public class DbHelper extends SQLiteOpenHelper {
	private static final int DATABASE_VERSION = 1;
	private static final String DATABASE_NAME = "vpods";
	private static final String TABLE_QUEST = "quest";
	private static final String KEY_ID = "id";
	private static final String KEY_QUES = "question";
	private static final String KEY_ANSWER = "answer"; //correct option
	private static final String KEY_OPTA= "opta"; //option a
	private static final String KEY_OPTB= "optb"; //option b
	private static final String KEY_OPTC= "optc"; //option c
	private SQLiteDatabase dbase;
	public DbHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	@Override
	public void onCreate(SQLiteDatabase db) {
		dbase = db;
		String sql = "CREATE TABLE IF NOT EXISTS " + TABLE_QUEST + " ( "
				+ KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_QUES
				+ " TEXT, " + KEY_ANSWER + " TEXT, " + KEY_OPTA + " TEXT, "
				+ KEY_OPTB + " TEXT, " + KEY_OPTC + " TEXT)";
		db.execSQL(sql);
		addQuestions();
	}

	private void addQuestions()
	{
		Question q1=new Question("Right now, GSM is the accepted cellular standard in ____________.","Europe", "South America", "SouthEast Asia", "Europe");
		this.addQuestion(q1);
		Question q2=new Question("Which area of the world first deployed cellular services for commercial use?", "Scandinavia ", "Central America","Western Africa ","Central America");
		this.addQuestion(q2);
		Question q3=new Question("Modulation refers to __________.","the distance between the uplink and downlink frequencies", "the separation between adjacent carrier frequencies ","the number of cycles per unit of time", "the separation between adjacent carrier frequencies " );
		this.addQuestion(q3);
		Question q4=new Question("The first cellular systems were ___________.", "analog", "digital", "semi analog","digital");
		this.addQuestion(q4);
		Question q5=new Question("The location area is the area in which a ___________ can be paged.","subscriber","BTS","tower","tower");
		this.addQuestion(q5);
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldV, int newV) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_QUEST);
		onCreate(db);
	}
	public void addQuestion(Question quest) {
		ContentValues values = new ContentValues();
		values.put(KEY_QUES, quest.getQUESTION());
		values.put(KEY_ANSWER, quest.getANSWER());
		values.put(KEY_OPTA, quest.getOPTA());
		values.put(KEY_OPTB, quest.getOPTB());
		values.put(KEY_OPTC, quest.getOPTC());
		dbase.insert(TABLE_QUEST, null, values);
	}
	public List<Question> getAllQuestions() {
		List<Question> quesList = new ArrayList<Question>();
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		dbase=this.getReadableDatabase();
		Cursor cursor = dbase.rawQuery(selectQuery, null);
		if (cursor.moveToFirst()) {
			do {
				Question quest = new Question();
				quest.setID(cursor.getInt(0));
				quest.setQUESTION(cursor.getString(1));
				quest.setANSWER(cursor.getString(2));
				quest.setOPTA(cursor.getString(3));
				quest.setOPTB(cursor.getString(4));
				quest.setOPTC(cursor.getString(5));
				quesList.add(quest);
			} while (cursor.moveToNext());
		}
		return quesList;
	}
	public int rowcount()
	{
		int row=0;
		String selectQuery = "SELECT  * FROM " + TABLE_QUEST;
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor cursor = db.rawQuery(selectQuery, null);
		row=cursor.getCount();
		return row;
	}
}
