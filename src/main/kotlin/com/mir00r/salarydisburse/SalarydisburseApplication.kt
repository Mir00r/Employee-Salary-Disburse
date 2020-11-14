package com.mir00r.salarydisburse;

import com.mir00r.salarydisburse.commons.Commons
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.ApplicationArguments
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.ConfigurableApplicationContext


@SpringBootApplication
open class SalarydisburseApplication {

    companion object {
        @Autowired
        var context: ConfigurableApplicationContext? = null

        @JvmStatic
        fun main(args: Array<String>) {
            context = runApplication<SalarydisburseApplication>(*args)
//            Commons.beep()
        }

        @JvmStatic
        fun restart() {
            val args = context?.getBean(ApplicationArguments::class.java)

            val thread = Thread {
                context?.close()
                context = SpringApplication.run(SalarydisburseApplication::class.java, *args?.sourceArgs)
            }

            thread.isDaemon = false
            thread.start()
        }
    }

}
