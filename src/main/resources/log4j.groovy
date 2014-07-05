log4j {

    appender.stdout = "org.apache.log4j.ConsoleAppender"
    appender."stdout.layout" = "org.apache.log4j.PatternLayout"
    appender."stdout.layout.ConversionPattern" = '%d{yyyy-MM-dd HH:mm:ss} DUPA %-5p %c{1}:%L - %m%n'

    appender.scrlog = "org.apache.log4j.FileAppender"
    appender."scrlog.layout" = "org.apache.log4j.TTCCLayout"
    appender."scrlog.file" = "script.log"

    // Direct log messages to a log file
    log4j.appender.file = org.apache.log4j.RollingFileAppender
    log4j.appender.file.File = /tmp/ boot.log
    log4j.appender.file.MaxFileSize = 10 MB
    log4j.appender.file.MaxBackupIndex = 1
    log4j.appender.file.layout = org.apache.log4j.PatternLayout
    log4j.appender.file.layout.ConversionPattern = % d % -5
    p[% c](% t)[% X { ip } - % X { username }] % X { requestURL } % m % n

    // Logging to graylog
    log4j.appender.gelf = org.graylog2.log.GelfAppender
    log4j.appender.gelf.name = gelf
    log4j.appender.gelf.addExtendedInformation = true
    log4j.appender.gelf.includeLocation = false
    log4j.appender.gelf.threshold = org.apache.log4j.Level.INFO
    log4j.appender.gelf.layout = % d % -5
    p[% c](% t)[% X { ip } - % X { username }] % X { requestURL } % m % n
    log4j.appender.gelf.additionalFields = { 'appRuntime': 'boot', 'appName': 'push-notification-sender', 'appEnvironment': 'production', 'appVersion': '0.1' }
    log4j.appender.gelf.graylogHost = logs.dev.outfittery.de


    rootLogger = "debug, scrlog, stdout"
}