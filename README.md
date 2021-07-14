# ThreadWorkerAndCoroutines
In this sample I have posted handlers and threads.

Here I have first created a handler and runnable , as we know handler post method runs on a runnable so don't need to define a separate runnable object.
AS -  Handler().postDelayed ({ log("Synchronous operation1")},2000 )
here postDelayed follows the cuncurrency and post the value on ui thread one after another if we have more than one handler object like :
Handler().postDelayed ({ log("Synchronous operation1")},2000 )
        Handler().postDelayed ({ log("Synchronous operation2")},1000 )
        Handler().postDelayed ({ log("Synchronous operation3")},3000 )
        
        here the log method is like this :
    
    @Suppress("SameParameterValue")
    private fun log(message: String) {
        Log.i(LOG_TAG, message)
        binding.logDisplay.append(message + "\n")
        scrollTextToEnd()
    }
    here logDisplay is a textview onto which we post the result from handler.
    
    And the other element is thread :
     thread(start = true) {
          for (i in 1..10){
              Log.i(LOG_TAG, "Looping value $i")
              Thread.sleep(1000)
          }
          Log.i(LOG_TAG,"completed")
        }
        Notice here we have printed log values and not posted on ui thread.
        
    
