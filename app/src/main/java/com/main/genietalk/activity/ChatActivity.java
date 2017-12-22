package com.main.genietalk.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PorterDuff;
import android.graphics.Rect;
import android.hardware.fingerprint.FingerprintManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.speech.RecognitionListener;
import android.speech.RecognizerIntent;
import android.speech.SpeechRecognizer;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;

import android.support.v13.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.github.orangegangsters.lollipin.lib.managers.AppLock;
import com.github.orangegangsters.lollipin.lib.managers.LockManager;
import com.main.genietalk.R;
import com.main.genietalk.adapter.ChatRecyclerAdapter;
import com.main.genietalk.db.DataBaseHelper;
import com.main.genietalk.expandable.MainActivity;
import com.main.genietalk.expandable.SimpleGestureFilter;
import com.main.genietalk.finger_prints.AuthenticationCallback;
import com.main.genietalk.finger_prints.FingerPrintHelperBuilder;
import com.main.genietalk.finger_prints.IFingerPrintUiHelper;
import com.main.genietalk.interfaceslistener.ChatInterfaceListener;
import com.main.genietalk.manager.NetworkConnection;
import com.main.genietalk.model.MenuExpandableListAdapter;
import com.main.genietalk.model.MenuModel;
import com.main.genietalk.service.LocationMonitoringService;
import com.main.genietalk.util.AESCrypt;

import com.main.genietalk.util.ChatMessage;
import com.main.genietalk.util.CircleTransform;
import com.main.genietalk.util.CommandBean;
import com.main.genietalk.util.DateUtils;
import com.main.genietalk.util.HttpHandler;
import com.main.genietalk.util.SharedPreferenceUtils;
import com.main.genietalk.util.ThemeSettingManager;
import com.main.genietalk.util.Utils;

import java.security.GeneralSecurityException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import lal.adhish.gifprogressbar.GifView;

import static com.main.genietalk.R.drawable.profileicon;
import static com.main.genietalk.R.id.messageEdit;

public class ChatActivity extends AppCompatActivity implements
        ChatInterfaceListener,
        AuthenticationCallback,
        SimpleGestureFilter.SimpleGestureListener,
        RecognitionListener {


    // tags used to attach the fragments
    private SimpleGestureFilter detector;
    private static final String TAG_HOME = "home";
    private static final String TAG_PHOTOS = "photos";
    private static final String TAG_MOVIES = "movies";
    private static final String TAG_NOTIFICATIONS = "notifications";
    private static final String TAG_SETTINGS = "settings";
    private static final int REQUEST_FINGER_PRINT_PERMISSION = 234;
    public static int navItemIndex = 0;
    public static String CURRENT_TAG = TAG_HOME;
    ArrayList<ChatMessage> chatMessagesList = new ArrayList<ChatMessage>();
    Dialog dialog = null;
    private ConstraintLayout mainlayout;

    //    private ListView messagesContainer;
    @BindView(R.id.messagesContainer)
    RecyclerView messagesContainer;


    private Button sendBtn;
    //    private ChatAdapter adapter;
    private ChatRecyclerAdapter chatRecyclerAdapter;
    private ArrayList<ChatMessage> chatHistory;
    private NavigationView navigationView;
    private DrawerLayout drawer;
    private View navHeader;
    private ImageView imgNavHeaderBg, imgProfile;
    private TextView txtName, txtWebsite;
    private Toolbar toolbar;
    private FloatingActionButton fab;
    private ExpandableListView menuExdpandable;
    private ArrayList<MenuModel> menuExpandableList;
    private MenuExpandableListAdapter menuExpandableListAdapter;
    // toolbar titles respected to selected nav menu item
    private String[] activityTitles;
    // flag to load home fragment when user presses back key
    private boolean shouldLoadHomeFragOnBackPress = true;
    private Handler mHandler;
    private long message_id = 0;
    private PrefManager prefManager;
    private long min_five = 300;  //5 min in seconds
    private IFingerPrintUiHelper fingerPrintUIHelper;
    //private SoftKeyboardStateWatcher softKeyboardStateWatcher;// = new SoftKeyboardStateWatcher(findViewById(R.id.container));
    private SpeechRecognizer speech = null;
    private Intent recognizerIntent;
    SharedPreferenceUtils sharedPreferenceUtils;
    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @BindView(R.id.progressBar1)
    ProgressBar progressBar1;
    @BindView(R.id.imageButton1)
    ImageButton imageButton1;
    @BindView(R.id.imageButton2)
    ImageButton imageButton2;
    @BindView(R.id.imageButton3)
    ImageButton imageButton3;
    @BindView(R.id.textView1)
    TextView resultTV;
    @BindView(R.id.progressBar_gif)
    GifView gifView;


    public static final int REQUEST_PERMISSION_LOCATION = 10;
    ChatInterfaceListener chatInterfaceListener;
    private EditText messageET;
    RelativeLayout drawer_layout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        sharedPreferenceUtils = SharedPreferenceUtils.getInstance(this);
        if (sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME, true)) {
            setTheme(R.style.ChatActThemeDark);
        } else {
            setTheme(R.style.ChatActThemeLight);
        }
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        ButterKnife.bind(this);
        init();
        chatInterfaceListener = this;
        progressBar1.setVisibility(View.INVISIBLE);
        prefManager = new PrefManager(this);
        drawer_layout = (RelativeLayout) findViewById(R.id.drawer_layout);


        initControls();
        mHandler = new Handler();
        detector = new SimpleGestureFilter(this, this);

        txtName = (TextView) findViewById(R.id.name);
        txtWebsite = (TextView) findViewById(R.id.website);
        imgNavHeaderBg = (ImageView) findViewById(R.id.img_header_bg);
        imgProfile = (ImageView) findViewById(R.id.img_profile);

        //Utils.getId("keybordwhite", R.drawable.class)
        changeMenu();
        initSpeech();
        startLocationUpdates();
        checkLocationPermission();
        startService(new Intent(this, LocationMonitoringService.class));
        DataBaseHelper dataBaseHelper = new DataBaseHelper(this);

        try {

            dataBaseHelper.createDataBase();
            dataBaseHelper.openDataBase();
            //dataBaseHelper.getData();


        } catch (Exception e) {
            e.printStackTrace();
        }

        if (NetworkConnection.checkConnection(this)) {
            enableChatMenu();
        } else {
            disableChatMenu();
        }
        imageButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNextActivity(R.anim.slide_up, R.anim.stay);
            }
        });
        /*try {
            if(NetworkConnection.checkConnection(ChatActivity.this)){
                enableChatMenu();
               if(sharedPreferenceUtils.getStringValue(Utils.USERID,null)==null){
                   new GetChatInit().execute();
               }

            }else{
                disableChatMenu();
            }
        } catch (Exception ex) {

        }*/
    }

    public boolean checkLocationPermission() {
        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {

            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                // Show an explanation to the user *asynchronously* -- don't block
                // this thread waiting for the user's response! After the user
                // sees the explanation, try again to request the permission.
                new AlertDialog.Builder(this)
                        //.setTitle(R.string.title_location_permission)
                        //.setMessage(R.string.text_location_permission)
                        .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                //Prompt the user once explanation has been shown
                                ActivityCompat.requestPermissions(ChatActivity.this,
                                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                                        REQUEST_PERMISSION_LOCATION);
                            }
                        })
                        .create()
                        .show();


            } else {
                // No explanation needed, we can request the permission.
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        REQUEST_PERMISSION_LOCATION);
            }
            return false;
        } else {
            return true;
        }
    }

    public void init() {

    }

    public void changeMenu() {


        /*if(sharedPreferenceUtils.getBoolanValue(Utils.IS_DARK_THEME,true)){
            imageButton1.setImageResource(R.drawable.keyboard);
            imageButton2.setImageResource(R.drawable.microphone);
            imageButton3.setImageResource(R.drawable.menu);
        }else{
            imageButton1.setImageResource(R.drawable.keybordwhite);
            imageButton2.setImageResource(R.drawable.microphonewhite);
            imageButton3.setImageResource(R.drawable.menuwhite);
        }*/
        String themeArra[] = Utils.convertString2Array(ChatActivity.this);

        //drawer_layout.setBackgroundResource(Utils.getId(themeArra[0], R.drawable.class));
        //Utils.getId("keybordwhite", R.drawable.class)
        imageButton1.setImageResource(Utils.getId(themeArra[8], R.drawable.class));
        imageButton2.setImageResource(Utils.getId(themeArra[9], R.drawable.class));
        imageButton3.setImageResource(Utils.getId(themeArra[10], R.drawable.class));
    }

    public void initSpeech() {
        speech = SpeechRecognizer.createSpeechRecognizer(this);
        speech.setRecognitionListener(this);
        recognizerIntent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        //recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE,"en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_PREFERENCE, "en");
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_CALLING_PACKAGE, this.getPackageName());
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_WEB_SEARCH);
        recognizerIntent.putExtra(RecognizerIntent.EXTRA_MAX_RESULTS, 3);
    }


    public void showInput(View v) {
        mainlayout = (ConstraintLayout) this.findViewById(R.id.chatControls);
        mainlayout.setVisibility(View.GONE);
        // messageET = (EditText) findViewById(messageEdit);
        sendBtn = (Button) findViewById(R.id.SendButton);
        messageET.setVisibility(View.VISIBLE);
        sendBtn.setVisibility(View.VISIBLE);
        messageET.requestFocus();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(messageET, InputMethodManager.SHOW_IMPLICIT);

    }

    public void showVoiceInput(View v) {
        speech.startListening(recognizerIntent);
    }

    public void showMenu(View v) {

        goToNextActivity(R.anim.slide_up, R.anim.stay);

    }

    private void goToNextActivity(int animationIn, int animationOut) {
        Intent intent = new Intent(ChatActivity.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        overridePendingTransition(animationIn, animationOut);
    }

    private void initControls() {
        mainlayout = (ConstraintLayout) this.findViewById(R.id.chatControls);
//        messagesContainer = (ListView) findViewById(R.id.messagesContainer);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, OrientationHelper.VERTICAL, false);
        messagesContainer.setLayoutManager(linearLayoutManager);
//        messagesContainer.hasFixedSize();
        messagesContainer.setHasFixedSize(false);

        messageET = (EditText) findViewById(messageEdit);
        sendBtn = (Button) findViewById(R.id.SendButton);
        messageET.setVisibility(View.GONE);
        sendBtn.setVisibility(View.GONE);
        RelativeLayout container = (RelativeLayout) findViewById(R.id.container);


        loadDummyHistory();

        messageET.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                if ((event != null && ((event.getKeyCode() == KeyEvent.KEYCODE_BACK) || (event.getKeyCode() == KeyEvent.KEYCODE_ENTER))) || (actionId == EditorInfo.IME_ACTION_DONE)) {
                    mainlayout.setVisibility(View.VISIBLE);
                    messageET.setVisibility(View.GONE);
                    sendBtn.setVisibility(View.GONE);
                    InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(messageET.getWindowToken(), 0);
                }
                return true;
            }
        });

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String messageText = messageET.getText().toString();
                if (TextUtils.isEmpty(messageText)) {
                    return;
                }
                //showLoader();
                mainlayout.setVisibility(View.GONE);
                messageET.setVisibility(View.GONE);
                sendBtn.setVisibility(View.GONE);
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(messageET.getWindowToken(), 0);
                addText(messageET.getText().toString());
                Log.e("SendData  :", " Data    " + messageET.getText().toString());

                try {
                    if (NetworkConnection.checkConnection(ChatActivity.this)) {
                        enableChatMenu();

                       /*if(CommandBean.getInstance().getCt()!=0){
                         new GetChatInit().execute();
                       }else{
                         new GetChatNew().execute();
                       }*/

                        new GetChatNew().execute();

                    } else {
                        disableChatMenu();
                    }
                } catch (Exception ex) {

                    ex.printStackTrace();

                }
            }
        });
    }

    public void addText(String message) {
        //mainlayout.setVisibility(View.VISIBLE);
        messageET.setVisibility(View.GONE);
        sendBtn.setVisibility(View.GONE);


        ChatMessage chatMessage1 = new ChatMessage();
        chatMessage1.setId(++message_id);
        chatMessage1.setMessage(message);
        chatMessage1.setDate(DateUtils.getCurrentDateTimeForDisplay());
        chatMessage1.setViewType(0);
        chatMessage1.setMe(false);
        displayMessage(chatMessage1);

    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        // user has long pressed your TextView
        menu.add(0, v.getId(), 0, "Copy");

        // cast the received View to TextView so that you can get its text
        TextView yourTextView = (TextView) v;

        // place your TextView's text in clipboard
        ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        clipboard.setText(yourTextView.getText());
    }

    @Override
    public void onAuthenticationError(int errMsgId, CharSequence errString) {
        Toast.makeText(this, errString, Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
        Toast.makeText(this, helpString, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onAuthenticationSucceeded(FingerprintManager.AuthenticationResult result) {
        Toast.makeText(this, getString(R.string.finger_prints_match), Toast.LENGTH_SHORT).show();
        if (dialog != null) {
            dialog.dismiss();
            fingerPrintUIHelper.stopListening();
        }
    }

    @Override
    public void onAuthenticationFailed() {
        Toast.makeText(this, getString(R.string.auth_failed), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == REQUEST_FINGER_PRINT_PERMISSION && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            startListeningFingerPrint();
        } else if (requestCode == REQUEST_PERMISSION_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // goAndDetectLocation();
            }
        }
    }

    private void startListeningFingerPrint() {
        //  if (fingerPrintUIHelper.initCipher()) {
        fingerPrintUIHelper.startListening();
        // } else {
        //show error
        //    Toast.makeText(this, getString(R.string.errore_generico), Toast.LENGTH_SHORT).show();

        // }
    }

    public void displayMessage(ChatMessage message) {
//        adapter.add(message);
//        adapter.notifyDataSetChanged();

        chatRecyclerAdapter.add(message);
        chatRecyclerAdapter.notifyDataSetChanged();
        scroll();
        hideLoader();
    }

    private void scroll() {
//        messagesContainer.setSelection(messagesContainer.getCount() - 1);
//        messagesContainer.smoothScrollToPosition(adapter.getItemCount() - 1);
    }

    private void loadDummyHistory() {
        chatHistory = new ArrayList<ChatMessage>();
        ChatMessage msg = new ChatMessage();
        msg.setId(0);
        msg.setMe(true);
        msg.setMessage("What Can I do For you Today?");
        msg.setDate(DateUtils.getCurrentDateTimeForDisplay());
        chatHistory.add(msg);
//        adapter = new ChatAdapter(ChatActivity.this, chatMessagesList,chatInterfaceListener);
//        messagesContainer.setAdapter(adapter);

        chatRecyclerAdapter = new ChatRecyclerAdapter(ChatActivity.this, chatHistory, chatInterfaceListener);
        messagesContainer.setAdapter(chatRecyclerAdapter);


//        for (int i = 0; i < chatHistory.size(); i++) {
//            ChatMessage message = chatHistory.get(i);
//            displayMessage(message);
//        }
    }

    /***
     * Load navigation menu header information
     * like background image, profile_icon image
     * name, website, notifications action view (dot)
     */
    private void loadNavHeader() {
        // name, website
        txtName.setText("Vivek Jain");
        txtWebsite.setText("Indore, India");
        // Loading profile_icon image
        Glide.with(this).load(profileicon)
                .crossFade()
                .thumbnail(0.5f)
                .bitmapTransform(new CircleTransform(this))
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .into(imgProfile);

    }

    /***
     * Returns respected fragment that user
     * selected from navigation menu
     */
    private void loadHomeFragment() {
        // selecting appropriate nav menu item
        selectNavMenu();

        // if user select the current navigation menu again, don't do anything
        // just close the navigation drawer
        if (getSupportFragmentManager().findFragmentByTag(CURRENT_TAG) != null) {
            drawer.closeDrawers();
            return;
        }

        Runnable mPendingRunnable = new Runnable() {
            @Override
            public void run() {
                // update the main content by replacing fragments
                /*Fragment fragment = getHomeFragment();
                FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
                fragmentTransaction.setCustomAnimations(android.R.anim.fade_in,
                        android.R.anim.fade_out);
                fragmentTransaction.replace(R.id.frame, fragment, CURRENT_TAG);
                fragmentTransaction.commitAllowingStateLoss();*/
            }
        };

        // If mPendingRunnable is not null, then add to the message queue
        if (mPendingRunnable != null) {
            mHandler.post(mPendingRunnable);
        }

        // show or hide the fab button
        //toggleFab();

        //Closing drawer on item click
        drawer.closeDrawers();

        // refresh toolbar menu
        invalidateOptionsMenu();
    }

    private void selectNavMenu() {
        navigationView.getMenu().getItem(navItemIndex).setChecked(true);
    }

    private void setUpNavigationView() {
        //Setting Navigation View Item Selected Listener to handle the item click of the navigation menu
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            // This method will trigger on item Click of navigation menu
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {

                //Check to see which item was being clicked and perform appropriate action
                switch (menuItem.getItemId()) {
                    //Replacing the main content with ContentFragment Which is our Inbox View;
                    case R.id.nav_profile:
                        navItemIndex = 0;
                        CURRENT_TAG = TAG_HOME;
                        break;
                    case R.id.nav_settings:
                        navItemIndex = 1;
                        CURRENT_TAG = TAG_PHOTOS;
                        break;
                    case R.id.nav_eliteclub:
                        navItemIndex = 2;
                        CURRENT_TAG = TAG_MOVIES;
                        break;
                    case R.id.nav_referandearn:
                        navItemIndex = 3;
                        CURRENT_TAG = TAG_NOTIFICATIONS;
                        break;
                    case R.id.nav_transactions:
                        navItemIndex = 4;
                        CURRENT_TAG = TAG_SETTINGS;
                        break;
                     /*case R.id.nav_about_us:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(ChatActivity.this, RegisterActivity.class));
                        drawer.closeDrawers();
                        return true;
                     case R.id.nav_privacy_policy:
                        // launch new intent instead of loading fragment
                        startActivity(new Intent(ChatActivity.this, RegisterActivity.class));
                        drawer.closeDrawers();
                        return true;*/
                    default:
                        navItemIndex = 0;
                }

                //Checking if the item is in checked state or not, if not make it in checked state
                if (menuItem.isChecked()) {
                    menuItem.setChecked(false);
                } else {
                    menuItem.setChecked(true);
                }
                menuItem.setChecked(true);
                loadHomeFragment();
                return true;
            }
        });

        ActionBarDrawerToggle actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.openDrawer, R.string.closeDrawer) {

            @Override
            public void onDrawerClosed(View drawerView) {
                // Code here will be triggered once the drawer closes as we dont want anything to happen so we leave this blank
                super.onDrawerClosed(drawerView);
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                // Code here will be triggered once the drawer open as we dont want anything to happen so we leave this blank
                super.onDrawerOpened(drawerView);
            }
        };

        //Setting the actionbarToggle to drawer layout
        drawer.setDrawerListener(actionBarDrawerToggle);

        //calling sync state is necessary or else your hamburger icon wont show up
        actionBarDrawerToggle.syncState();
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        switch (keyCode) {
            case KeyEvent.KEYCODE_BACK:

                mainlayout.setVisibility(View.VISIBLE);
                messageET.setVisibility(View.GONE);
                sendBtn.setVisibility(View.GONE);
                moveTaskToBack(true);
                return true;
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        prefManager.setOnPaus(true);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawers();
            return;
        }
        if (shouldLoadHomeFragOnBackPress) {
            if (navItemIndex != 0) {
                navItemIndex = 0;
                CURRENT_TAG = TAG_HOME;
                loadHomeFragment();
                return;
            }
        } else {
            super.onBackPressed();
        }
    }

    /*vinay*/

    public Collection<String> query(String queryStr, String queryStr2) {
        ArrayList<String> list = new ArrayList<String>();
        try {
            for (ChatMessage obj : chatMessagesList) {
                if (obj != null) {
                    String str = obj.getMessage().toLowerCase();

                    if (queryStr != null && queryStr2 != null) {
                        if (str.matches(queryStr.toLowerCase()) || str.contains(queryStr.toLowerCase()) || (str.matches("(?i)(" + queryStr + ").*"))
                                ||
                                (str.matches(queryStr2.toLowerCase()) || str.contains(queryStr2.toLowerCase()) || (str.matches("(?i)(" + queryStr2 + ").*")))) {
                            list.add(str);
                        }
                    } else if (queryStr != null) {
                        if (str.matches(queryStr.toLowerCase()) || str.contains(queryStr.toLowerCase()) || (str.matches("(?i)(" + queryStr + ").*"))) {
                            list.add(str);
                        }
                    } else if (queryStr2 != null) {
                        if (str.matches(queryStr2.toLowerCase()) || str.contains(queryStr2.toLowerCase()) || (str.matches("(?i)(" + queryStr2 + ").*"))) {
                            list.add(str);
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (list.isEmpty())
            return null;
        else
            return list;
    }

    public long secondsAgo(long datetime_inmili) {
        try {
            Calendar now = Calendar.getInstance(); // Get time now
            long differenceInMillis = now.getTimeInMillis() - datetime_inmili;
            long sec = differenceInMillis / 1000 % 60;
            // long differenceInHours = (differenceInMillis) / 1000L / 60L / 60L; // Divide by millis/sec, secs/min, mins/hr
            return (int) sec;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onResume() {
        super.onResume();
        //softKeyboardStateWatcher = new SoftKeyboardStateWatcher(findViewById(R.id.drawer_layout));
        long milisec = prefManager.getTimeOnResume();
        long sec = secondsAgo(milisec);
        if (sec > min_five) {
            if (chatMessagesList != null) {
                for (int i = 2; i < chatMessagesList.size() - 2 && chatMessagesList.size() > 3; i++) {
                    chatMessagesList.remove(i);
                }
                if (chatRecyclerAdapter != null)
                    chatRecyclerAdapter.notifyDataSetChanged();
            }
        }

        Log.v("vinay", "Pause" + prefManager.getOnPaus());

        LockManager<CustomPinActivity> lockManager = LockManager.getInstance();
        if (lockManager.isAppLockEnabled() && lockManager.getAppLock().isPasscodeSet() && prefManager.getOnPaus()) {
            prefManager.setOnPaus(false);
            Intent intent = new Intent(ChatActivity.this, CustomPinActivity.class);
            intent.putExtra(AppLock.EXTRA_TYPE, AppLock.UNLOCK_PIN);
            startActivity(intent);
        } else {
            if (prefManager.getFingerPrintEnable() && prefManager.getOnPaus()) {
                prefManager.setOnPaus(false);
                new Handler(Looper.getMainLooper()).post(new Runnable() { // Tried new Handler(Looper.myLopper()) also
                    @Override
                    public void run() {
                        initFingerPrint();
                    }
                });

            } else
                prefManager.setOnPaus(true);
        }
    }


    @Override
    protected void onPause() {
        super.onPause();
        Calendar date = Calendar.getInstance();
        prefManager.setTimeOnPaus(date.getTimeInMillis());

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void openDialogForFingerPrint() {
        // initFingerPrint();
        dialog = new Dialog(ChatActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_finger_print);
        dialog.setCanceledOnTouchOutside(false);
        dialog.setCancelable(false);

        dialog.show();

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initFingerPrint() {

        fingerPrintUIHelper = FingerPrintHelperBuilder.getFingerPrintUIHelper(ChatActivity.this, ChatActivity.this);

        // crypto = new FingerprintManagerCompat.CryptoObject(mChifer);


        if (!fingerPrintUIHelper.isHardwareDetected()) {
            // Device doesn't support fingerprint authentication
            Toast.makeText(this, getString(R.string.no_finger_senso), Toast.LENGTH_SHORT).show();

        } else if (!fingerPrintUIHelper.hasEnrolledFingerprints()) {
            // User hasn't enrolled any fingerprints to authenticate with
            Toast.makeText(this, getString(R.string.note), Toast.LENGTH_SHORT).show();

        } else {
            // Everything is ready for fingerprint authentication

            if (ActivityCompat.checkSelfPermission(this,
                    Manifest.permission.USE_FINGERPRINT) !=
                    PackageManager.PERMISSION_GRANTED) {

                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.USE_FINGERPRINT},
                        REQUEST_FINGER_PRINT_PERMISSION);

            } else {

                openDialogForFingerPrint();
                startListeningFingerPrint();
            }

        }
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent me) {
        // Call onTouchEvent of SimpleGestureFilter class
        this.detector.onTouchEvent(me);
        return super.dispatchTouchEvent(me);
    }

    @Override
    public void onSwipe(int direction) {
        String str = "";

        switch (direction) {

            case SimpleGestureFilter.SWIPE_RIGHT:
                str = "Swipe Right";
                break;
            case SimpleGestureFilter.SWIPE_LEFT:
                str = "Swipe Left";
                break;
            case SimpleGestureFilter.SWIPE_DOWN:
                str = "Swipe Down";
                //slidingDrawer.open();
                break;
            case SimpleGestureFilter.SWIPE_UP:
                str = "Swipe Up";
                //slidingDrawer.close();
                //showMenu(null);
                break;

        }
        // Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public static String getErrorText(int errorCode) {
        String message;
        switch (errorCode) {
            case SpeechRecognizer.ERROR_AUDIO:
                message = "Audio recording error";
                break;
            case SpeechRecognizer.ERROR_CLIENT:
                message = "Client side error";
                break;
            case SpeechRecognizer.ERROR_INSUFFICIENT_PERMISSIONS:
                message = "Insufficient permissions";
                break;
            case SpeechRecognizer.ERROR_NETWORK:
                message = "Network error";
                break;
            case SpeechRecognizer.ERROR_NETWORK_TIMEOUT:
                message = "Network timeout";
                break;
            case SpeechRecognizer.ERROR_NO_MATCH:
                message = "No match";
                break;
            case SpeechRecognizer.ERROR_RECOGNIZER_BUSY:
                message = "RecognitionService busy";
                break;
            case SpeechRecognizer.ERROR_SERVER:
                message = "error from server";
                break;
            case SpeechRecognizer.ERROR_SPEECH_TIMEOUT:
                message = "No speech input";
                break;
            default:
                message = "Didn't understand, please try again.";
                break;
        }
        return message;
    }

    public void showLoader() {
        disableChatMenu();
        gifView.setVisibility(View.VISIBLE);
        ThemeSettingManager themeSettingManager = ThemeSettingManager.getInstance();
        themeSettingManager.setLoader(this, gifView);
        mainlayout.setVisibility(View.GONE);
        //gifView.setImageResource(R.drawable.loader_black);
        //gifView.setImageResource(R.drawable.loading_spinner);
    }

    public void hideLoader() {
        enableChatMenu();
        gifView.setVisibility(View.GONE);
        mainlayout.setVisibility(View.VISIBLE);
    }

    public void enableChatMenu() {
        imageButton1.setEnabled(true);
        imageButton2.setEnabled(true);
    }

    public void disableChatMenu() {
        imageButton1.setEnabled(false);
        imageButton2.setEnabled(false);
    }


    @Override
    public void onDoubleTap() {

    }

    @Override
    public void onReadyForSpeech(Bundle bundle) {

    }

    @Override
    public void onBeginningOfSpeech() {

        showLoader();
        progressBar1.setVisibility(View.GONE);
        progressBar1.setIndeterminate(false);
        progressBar1.setMax(10);
    }

    @Override
    public void onRmsChanged(float v) {

    }

    @Override
    public void onBufferReceived(byte[] bytes) {

    }

    @Override
    public void onEndOfSpeech() {
        hideLoader();
        progressBar1.setVisibility(View.INVISIBLE);
        progressBar1.setIndeterminate(true);
        // speech.stopListening();
    }

    @Override
    public void onError(int errorCode) {
        String errorMessage = getErrorText(errorCode);
        // speech.stopListening();
        // resultTV.setText(errorMessage);
    }

    @Override
    public void onResults(Bundle bundle) {
        ArrayList<String> matches = bundle.getStringArrayList(SpeechRecognizer.RESULTS_RECOGNITION);
        /*String text = "";
        for (String result : matches)
            text += result + "\n";*/
        String text = matches.get(0);
        //resultTV.setText(text);
        messageET.setText(text);
        sendBtn.performClick();
    }

    @Override
    public void onPartialResults(Bundle bundle) {

    }

    @Override
    public void onEvent(int i, Bundle bundle) {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (speech != null) {
            speech.destroy();
        }
    }

    protected void startLocationUpdates() {

        if (ActivityCompat.checkSelfPermission(ChatActivity.this, android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(ChatActivity.this, new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION}, REQUEST_PERMISSION_LOCATION);
        } else {
            // goAndDetectLocation();
        }

    }

    public void callService() {
        try {
            if (NetworkConnection.checkConnection(ChatActivity.this)) {
                enableChatMenu();
                //doTheTask(new GetChatNew());
                new GetChatNew().execute();
            } else {
                disableChatMenu();
            }
        } catch (Exception ex) {
        }
    }

    void doTheTask(AsyncTask task) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) { // Android 4.4 (API 19) and above
            // Parallel AsyncTasks are possible, with the thread-pool size dependent on device
            // hardware
            task.execute();
        } else if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) { // Android 3.0 to
            // Android 4.3
            // Parallel AsyncTasks are not possible unless using executeOnExecutor
            task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else { // Below Android 3.0
            // Parallel AsyncTasks are possible, with fixed thread-pool size
            task.execute();
        }
    }


    @Override
    public void setIndex(String index) {
        messageET.setText(index);
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                sendBtn.performClick();
            }
        });

        // callService();
    }


    private class GetChatInit extends AsyncTask<Void, Void, Void> {
        ChatMessage chatMessage3 = new ChatMessage();
        // URL to get contacts JSON
        Date oldDate1 = new Date();
        AESCrypt aes = new AESCrypt();
        private String TAG = ChatActivity.class.getSimpleName();


        /*String encMsg = "b64d<)E$||" + messageET.getText().toString() + "||";
        private String url = "http://www.divineplayschool.com/genietalk/api/rest.php";*/
        String encMsg1 = sharedPreferenceUtils.getStringValue(Utils.USERID, "0") + "|||" + DateUtils.getCurrentDate() + "|||" + sharedPreferenceUtils.getStringValue(Utils.RAPARAM, "0")
                + "|||" + messageET.getText().toString() + "|||" + Utils.getCommand() + "|||" + Utils.getVersion(ChatActivity.this) + ";" + Utils.getDeviceId(ChatActivity.this) + "|||";
        String encMsg2 = "a1d4da19465a|||29-Nov-2017 at 10:53:33 AM|||0|||book me a flight from IDR to PNQ on 2017-12-18|||0|||83E786B0-6B59-41FE-ADE3-FC5A9DB97CF2";
        String encMsg = sharedPreferenceUtils.getStringValue(Utils.USERID, "5a1c171be0ac2") + "|||" + DateUtils.getCurrentDate() + "|||" + sharedPreferenceUtils.getStringValue(Utils.RAPARAM, "0")
                + "|||" + messageET.getText().toString() + "|||" + CommandBean.getInstance().getCt() + "|||" + Utils.getVersion(ChatActivity.this) + ";" + Utils.getDeviceId(ChatActivity.this) + "|||";
        private String url = Utils.BASEURL + "api.php";
        private String postdata = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            chatMessage3.setGifload(true);
            chatMessage3.setMe(true);
            displayMessage(chatMessage3);
            showLoader();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            try {
                encMsg = aes.bytesToHex(aes.encrypt(encMsg));
                Log.e(TAG, "Response encMsg : " + encMsg);
            } catch (Exception e) {
                //handle error
                Log.e(TAG, String.valueOf(e));
            }
            postdata = "q=" + encMsg;
            // Making a request to url and getting response
            String retStr = sh.makeServiceCall(url, postdata);

            if (retStr != null) {
                try {
                    String messageAfterDecrypt = "";
                    try {
                        messageAfterDecrypt = new String(aes.decrypt(retStr));
                        Log.e(TAG, "Response messageAfterDecrypt: " + messageAfterDecrypt);
                    } catch (GeneralSecurityException e) {
                        //handle error - could be due to incorrect password or tampered encryptedMsg
                    }
                    final String[] ans = messageAfterDecrypt.split("\\|\\|\\|");
                    //Log.e(TAG, "dcd: " + ans[1]);

                    if (ans[0].equals("gts")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    /*if (chatMessage3 != null && chatMessage3.isGifload()) {
                                        chatMessagesList.remove(chatMessagesList.size() - 1);
                                        adapter.notifyDataSetChanged();
                                    }*/


                                    if (ans[3].length() > 0 && CommandBean.getInstance().getCt() == 1) {
                                        sharedPreferenceUtils.setValue(Utils.USERID, ans[3]);
                                    }

                                    if (Integer.parseInt(ans[8]) != CommandBean.getInstance().getCt()) {
                                        CommandBean.getInstance().setCt(Integer.parseInt(ans[8]));
                                    }

                                    ChatMessage chatMessage2 = new ChatMessage();
                                    chatMessage2.setId(++message_id);
                                    chatMessage2.setMessage(ans[5]);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date oldDate2 = null;
                                    try {
                                        oldDate2 = dateFormat.parse(ans[1].toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Date currentDate = new Date();
                                    long diff1 = currentDate.getTime() - oldDate1.getTime();
                                    long seconds1 = diff1;  /// 1000;
                                    long diff2 = currentDate.getTime() - oldDate2.getTime();
                                    long seconds2 = diff2;  /// 1000;

                                    try {
                                        chatMessage2.setDate(DateUtils.convertSqlDateToDisplayDate(ans[1]) + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        chatMessage2.setDate(ans[1] + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                    }

                                    chatMessage2.setViewType(Integer.parseInt(ans[2]));
                                    chatMessage2.setGifload(false);
                                    chatMessage2.setMe(true);
                                    displayMessage(chatMessage2);
                                    if (Integer.parseInt(ans[3]) == 1) {
                                        sharedPreferenceUtils.setValue(Utils.RAPARAM, ans[4]);
                                    } else {
                                        sharedPreferenceUtils.setValue(Utils.RAPARAM, "0");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                } catch (final Exception e) {
                    //Log.e(TAG, "Parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatMessagesList.remove(chatMessagesList.size() - 1);
                        Toast.makeText(getApplicationContext(),
                                "",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            hideLoader();
            messageET.setText("");

            /*
              Updating parsed JSON data into ListView
             ListAdapter adapter = new SimpleAdapter(
             R.layout.list_item, new String[]{"name", "email",
             "mobile"}, new int[]{R.id.name,
             R.id.email, R.id.mobile});
             lv.setAdapter(adapter);*/

        }
    }


    private class GetChatNew extends AsyncTask<Void, Void, Void> {
        ChatMessage chatMessage3 = new ChatMessage();
        // URL to get contacts JSON
        Date oldDate1 = new Date();
        AESCrypt aes = new AESCrypt();
        private String TAG = ChatActivity.class.getSimpleName();


        /*String encMsg = "b64d<)E$||" + messageET.getText().toString() + "||";
        private String url = "http://www.divineplayschool.com/genietalk/api/rest.php";*/
        String encMsg1 = sharedPreferenceUtils.getStringValue(Utils.USERID, "0") + "|||" + DateUtils.getCurrentDate() + "|||" + sharedPreferenceUtils.getStringValue(Utils.RAPARAM, "0")
                + "|||" + messageET.getText().toString() + "|||" + Utils.getCommand() + "|||" + Utils.getVersion(ChatActivity.this) + ";" + Utils.getDeviceId(ChatActivity.this) + "|||";
        String encMsg2 = "a1d4da19465a|||29-Nov-2017 at 10:53:33 AM|||0|||book me a flight from IDR to PNQ on 2017-12-18|||0|||83E786B0-6B59-41FE-ADE3-FC5A9DB97CF2";
        String encMsg = sharedPreferenceUtils.getStringValue(Utils.USERID, "5a1c171be0ac2") + "|||" + DateUtils.getCurrentDate() + "|||" + sharedPreferenceUtils.getStringValue(Utils.RAPARAM, "0")
                + "|||" + messageET.getText().toString() + "|||" + Utils.getCommand() + "|||" + Utils.getVersion(ChatActivity.this) + ";" + Utils.getDeviceId(ChatActivity.this) + "|||";
        private String url = Utils.BASEURL + "api.php";
        private String postdata = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            chatMessage3.setGifload(true);
            chatMessage3.setMe(true);
            displayMessage(chatMessage3);
            showLoader();
        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            try {
                encMsg = aes.bytesToHex(aes.encrypt(encMsg));
                Log.e(TAG, "Response encMsg : " + encMsg);
            } catch (Exception e) {
                //handle error
                Log.e(TAG, String.valueOf(e));
            }
            postdata = "q=" + encMsg;
            // Making a request to url and getting response
            String retStr = sh.makeServiceCall(url, postdata);

            if (retStr != null) {
                try {
                    String messageAfterDecrypt = "";
                    try {
                        messageAfterDecrypt = new String(aes.decrypt(retStr));
                        Log.e(TAG, "Response messageAfterDecrypt: " + messageAfterDecrypt);
                    } catch (GeneralSecurityException e) {
                        //handle error - could be due to incorrect password or tampered encryptedMsg
                    }
                    final String[] ans = messageAfterDecrypt.split("\\|\\|\\|");
                    //Log.e(TAG, "dcd: " + ans[1]);

                    if (ans[0].equals("gts")) {
                        /*Handler handler = new Handler();
                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                {
                                    try {
                                    *//*if (chatMessage3 != null && chatMessage3.isGifload()) {
                                        chatMessagesList.remove(chatMessagesList.size() - 1);
                                        adapter.notifyDataSetChanged();
                                    }*//*
                                        ChatMessage chatMessage2 = new ChatMessage();
                                        chatMessage2.setId(++message_id);
                                        chatMessage2.setMessage(ans[5]);
                                        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                        Date oldDate2 = null;
                                        try {
                                            oldDate2 = dateFormat.parse(ans[1].toString());
                                        } catch (ParseException e) {
                                            e.printStackTrace();
                                        }
                                        Date currentDate = new Date();
                                        long diff1 = currentDate.getTime() - oldDate1.getTime();
                                        long seconds1 = diff1;  /// 1000;
                                        long diff2 = currentDate.getTime() - oldDate2.getTime();
                                        long seconds2 = diff2;  /// 1000;

                                        try {
                                            chatMessage2.setDate(DateUtils.convertSqlDateToDisplayDate(ans[1]) + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                        }catch (Exception e){
                                            e.printStackTrace();
                                            chatMessage2.setDate(ans[1] + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                        }

                                        chatMessage2.setViewType(Integer.parseInt(ans[2]));
                                        chatMessage2.setGifload(false);
                                        chatMessage2.setMe(true);
                                        displayMessage(chatMessage2);
                                        if (Integer.parseInt(ans[3]) == 1) {
                                            sharedPreferenceUtils.setValue(Utils.RAPARAM, ans[4]);
                                        }else{
                                            sharedPreferenceUtils.setValue(Utils.RAPARAM, "0");
                                        }
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });*/
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    /*if (chatMessage3 != null && chatMessage3.isGifload()) {
                                        chatMessagesList.remove(chatMessagesList.size() - 1);
                                        adapter.notifyDataSetChanged();
                                    }*/
                                    ChatMessage chatMessage2 = new ChatMessage();
                                    chatMessage2.setId(++message_id);
                                    chatMessage2.setMessage("https://www.google.co.in");
                                    //chatMessage2.setMessage(ans[5]);
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    Date oldDate2 = null;
                                    try {
                                        oldDate2 = dateFormat.parse(ans[1].toString());
                                    } catch (ParseException e) {
                                        e.printStackTrace();
                                    }
                                    Date currentDate = new Date();
                                    long diff1 = currentDate.getTime() - oldDate1.getTime();
                                    long seconds1 = diff1;  /// 1000;
                                    long diff2 = currentDate.getTime() - oldDate2.getTime();
                                    long seconds2 = diff2;  /// 1000;

                                    try {
                                        chatMessage2.setDate(DateUtils.convertSqlDateToDisplayDate(ans[1]) + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                        chatMessage2.setDate(ans[1] + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                    }

                                    //chatMessage2.setViewType(Integer.parseInt(ans[2]));
                                    chatMessage2.setViewType(1);
                                    chatMessage2.setGifload(false);
                                    chatMessage2.setMe(true);
                                    displayMessage(chatMessage2);
                                    if (Integer.parseInt(ans[3]) == 1) {
                                        sharedPreferenceUtils.setValue(Utils.RAPARAM, ans[4]);
                                    } else {
                                        sharedPreferenceUtils.setValue(Utils.RAPARAM, "0");
                                    }
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                        });
                    }

                } catch (final Exception e) {
                    //Log.e(TAG, "Parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatMessagesList.remove(chatMessagesList.size() - 1);
                        Toast.makeText(getApplicationContext(),
                                "",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog

            hideLoader();
            messageET.setText("");

            /*
              Updating parsed JSON data into ListView
             ListAdapter adapter = new SimpleAdapter(
             R.layout.list_item, new String[]{"name", "email",
             "mobile"}, new int[]{R.id.name,
             R.id.email, R.id.mobile});
             lv.setAdapter(adapter);*/

        }
    }


    private class GetChat extends AsyncTask<Void, Void, Void> {
        ChatMessage chatMessage3 = new ChatMessage();
        // URL to get contacts JSON
        Date oldDate1 = new Date();
        AESCrypt aes = new AESCrypt();
        private String TAG = ChatActivity.class.getSimpleName();
        private ProgressDialog pDialog;
        private ListView lv;
        private EditText messageET = (EditText) findViewById(messageEdit);
        String encMsg = "b64d<)E$||" + messageET.getText().toString() + "||";
        private String url = "http://www.divineplayschool.com/genietalk/api/rest.php";
        /*String encMsg = sharedPreferenceUtils.getStringValue(Utils.USER_ID,"0")+"|||"+ DateUtils.getCurrentDate()+" at "+DateUtils.getCurrentTime()+"|||"+sharedPreferenceUtils.getStringValue(Utils.RA_PARAM,"0")
               +"|||hi|||0|||"+Utils.getDeviceId(ChatActivity.this)+"|||";
        private String url = "http://192.168.1.92/genieapptest/api/version1/api.php";*/
        private String postdata = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            chatMessage3.setGifload(true);
            chatMessage3.setMe(true);
            displayMessage(chatMessage3);

        }

        @Override
        protected Void doInBackground(Void... arg0) {
            HttpHandler sh = new HttpHandler();
            try {
                encMsg = aes.bytesToHex(aes.encrypt(encMsg));
                Log.e(TAG, "Response encMsg : " + encMsg);
            } catch (Exception e) {
                //handle error
                Log.e(TAG, String.valueOf(e));
            }
            postdata = "q=" + encMsg;
            // Making a request to url and getting response
            String retStr = sh.makeServiceCall(url, postdata);

            if (retStr != null) {
                try {
                    String messageAfterDecrypt = "";
                    try {
                        messageAfterDecrypt = new String(aes.decrypt(retStr));
                        Log.e(TAG, "Response messageAfterDecrypt: " + messageAfterDecrypt);
                    } catch (GeneralSecurityException e) {
                        //handle error - could be due to incorrect password or tampered encryptedMsg
                    }
                    final String[] ans = messageAfterDecrypt.split("\\|\\|");
                    //Log.e(TAG, "dcd: " + ans[1]);
                    if (ans[0].equals("gts")) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (chatMessage3 != null && chatMessage3.isGifload()) {
                                    chatMessagesList.remove(chatMessagesList.size() - 1);
//                                    adapter.notifyDataSetChanged();
                                    chatRecyclerAdapter.notifyDataSetChanged();
                                }
                                ChatMessage chatMessage2 = new ChatMessage();
                                chatMessage2.setId(++message_id);
                                chatMessage2.setMessage(ans[3]);
                                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                Date oldDate2 = null;
                                try {
                                    oldDate2 = dateFormat.parse(ans[1].toString());
                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                Date currentDate = new Date();
                                long diff1 = currentDate.getTime() - oldDate1.getTime();
                                long seconds1 = diff1;/// 1000;
                                long diff2 = currentDate.getTime() - oldDate2.getTime();
                                long seconds2 = diff2;/// 1000;
                                chatMessage2.setDate(ans[1] + " (" + seconds2 + "ms/" + seconds1 + "ms)");
                                chatMessage2.setViewType(Integer.parseInt(ans[2]));
                                chatMessage2.setGifload(false);
                                chatMessage2.setMe(true);
                                displayMessage(chatMessage2);

                            }
                        });
                    }
                } catch (final Exception e) {
                    //Log.e(TAG, "Parsing error: " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Json parsing error: " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });
                }
            } else {
                Log.e(TAG, "Couldn't get json from server.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        chatMessagesList.remove(chatMessagesList.size() - 1);
                        Toast.makeText(getApplicationContext(),
                                "",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });
            }
            return null;
        }


        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);
            // Dismiss the progress dialog
           /* if (pDialog.isShowing())
                pDialog.dismiss();*/
            messageET.setText("");

            /**
             * Updating parsed JSON data into ListView
             *
             ListAdapter adapter = new SimpleAdapter(

             R.layout.list_item, new String[]{"name", "email",
             "mobile"}, new int[]{R.id.name,
             R.id.email, R.id.mobile});

             lv.setAdapter(adapter);*/

            //    query("calc","hi");
        }
    }


}


class SoftKeyboardStateWatcher implements ViewTreeObserver.OnGlobalLayoutListener {

    private final List<SoftKeyboardStateListener> listeners = new LinkedList<SoftKeyboardStateListener>();
    private final View activityRootView;
    private int lastSoftKeyboardHeightInPx;
    private boolean isSoftKeyboardOpened;

    public SoftKeyboardStateWatcher(View activityRootView) {
        this(activityRootView, false);
    }

    public SoftKeyboardStateWatcher(View activityRootView, boolean isSoftKeyboardOpened) {
        this.activityRootView = activityRootView;
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
        activityRootView.getViewTreeObserver().addOnGlobalLayoutListener(this);
    }

    @Override
    public void onGlobalLayout() {
        final Rect r = new Rect();
        //r will be populated with the coordinates of your view that area still visible.
        activityRootView.getWindowVisibleDisplayFrame(r);

        final int heightDiff = activityRootView.getRootView().getHeight() - (r.bottom - r.top);
        if (!isSoftKeyboardOpened && heightDiff > 100) { // if more than 100 pixels, its probably a keyboard...
            isSoftKeyboardOpened = true;
            notifyOnSoftKeyboardOpened(heightDiff);
        } else if (isSoftKeyboardOpened && heightDiff < 100) {
            isSoftKeyboardOpened = false;
            notifyOnSoftKeyboardClosed();
        }
    }

    public void setIsSoftKeyboardOpened(boolean isSoftKeyboardOpened) {
        this.isSoftKeyboardOpened = isSoftKeyboardOpened;
    }

    public boolean isSoftKeyboardOpened() {
        return isSoftKeyboardOpened;
    }

    /**
     * Default value is zero {@code 0}.
     *
     * @return last saved keyboard height in px
     */
    public int getLastSoftKeyboardHeightInPx() {
        return lastSoftKeyboardHeightInPx;
    }

    public void addSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.add(listener);
    }

    public void removeSoftKeyboardStateListener(SoftKeyboardStateListener listener) {
        listeners.remove(listener);
    }

    private void notifyOnSoftKeyboardOpened(int keyboardHeightInPx) {
        this.lastSoftKeyboardHeightInPx = keyboardHeightInPx;

        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardOpened(keyboardHeightInPx);
            }
        }
    }

    private void notifyOnSoftKeyboardClosed() {
        for (SoftKeyboardStateListener listener : listeners) {
            if (listener != null) {
                listener.onSoftKeyboardClosed();
            }
        }
    }

    public interface SoftKeyboardStateListener {
        void onSoftKeyboardOpened(int keyboardHeightInPx);

        void onSoftKeyboardClosed();
    }

}