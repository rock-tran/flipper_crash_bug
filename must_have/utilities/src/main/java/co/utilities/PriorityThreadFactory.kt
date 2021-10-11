package co.utilities

import java.util.concurrent.ThreadFactory

class PriorityThreadFactory(// priority of thread based on  Linux priorities
    // A Linux priority level, from -20 for highest scheduling priority to 19 for lowest scheduling priority.
    private val mThreadPriority: Int
) : ThreadFactory {

    override fun newThread(runnable: Runnable): Thread {
        val wrapperRunnable = Runnable {
            try {
                android.os.Process.setThreadPriority(mThreadPriority)
            } catch (t: Throwable) {
            }

            runnable.run()
        }
        return Thread(wrapperRunnable)
    }
}