what I used debugging
Hi FuzzProductons, 

This is my submission for the mobile test. Building this, I used stackoverflow when I wanted some guidance.
This led me to some places whose (opensource) code I used. http://www.androidhive.info/2012/01/android-json-parsing-tutorial/
helped me brush up on json parsing, and I used their servicehandler class.
 https://github.com/thest1/LazyList provided a good way to load and cache images. http://logc.at/2011/10/10/handling-listviews-with-multiple-row-types/
 helped me think through organizing the data and creating the arrayadapter.
  http://wptrafficanalyzer.in/blog/implementing-horizontal-view-swiping-using-viewpager-and-fragmentpageradapter-in-android/ helped me get used to viewpagers.
  I also used the developer docs for reviewing creating a tab interface. This looking for good examples and best practices is
  the way I typically develop, and I hope it has been in the spirit of the assignment.
  
  Loading images using this sort of library is apparently the preferred way to do this. I found the competing libraries
  inferior or less accessible. I did take the image loader apart and understand
  what each piece does. I'll outline all classes used here.
  
 ------------------
 ImageLoader
 ------------------
 
 ImageLoader -- if it's cached, get it and send it right back, end of story, no threading used
 if not, add the image to the queue and put out the placeholder image for now
 When the photoloader comes up in the queue, it gets the image from the url, and saves it in the cache. Then it
  posts to the ui thread using handler.post that it is done and runs a bitmap displayer runnable on the ui thread
   to update the imageview
  
  Utils - has a utility function for copying bits
  
  filecache- takes care of the addresses of locally stored bitmaps
  
  memorycache - maintains a map of cached bitmaps stored by their urls
 ------------------
 
 Main Activity
  --------------
 MainActivity - A listactivity On launch, querys json data using asynch task. It then fills 3 arraylists, one with all data, one with text
 and one with images. It has a tab interface, using a viewpager and 3 fragments. When tabs are switched, the main class supplies the new
 active fragment with the appropriate arrayadapter.
  
 ServiceHandler - returns data as string from url it's given
 
 WebActivity - launches a webview with the url it's given
 -----------------
 Row
 ----------------
 
 JsonLstElement - Java object that has string elements id, type and data
 
 RowType- enum for two types of Rows

 Row- Interface, contract ensures that all rows have the getView and getViewType methods
 
 TextRow - row element for objects with text rather than images. Inflates the text_item layout and sets the text from the 
 JsonListElement object. This object uses the viewHolder pattern to avoid excessive findViewById
 calls.
 
 ImageRow - row element for objects with images rather than text. Inflates the image_item layout and uses the imageloader to 
 get the image from the url in the JsonListElement Object. This object uses the viewHolder pattern to avoid excessive findViewById
 calls.
 
 LazyAdapterAssignsImageLayoutorTextLayoutBasedOnType - This adapter takes an arraylist of JsonLstElement objects. It 
 loads them into an arrayadapter using the appropriate layout, text or image
 -----------------
 Tabs
 ----------------
 MyFragmentPagerAdapter - pager adapter launches the fragments depending on position
 
 BaseFragment - base class for fragments, launches webview on list element click and provides method for activity to supply
 arrayadapter. also extends listfragment
 
 AllFragment, TextFragment, ImageFragment - These three extend BaseFragment and define interfaces for communicating with the mainactivity. 
 Displays list of elements in the arrayadapter.
 
 ---------
 
  
  I enjoyed this test and learned quite a bit. Thank you for that FuzzProductons, and good luck.
  
  p.s. Many of the links in the json are dead. http://dev.fuzzproductions.com/iPhoneTest/itunes_icon.jpeg
   could be fixed by changing it to  http://dev.fuzzproductions.com/MobileTest/itunes