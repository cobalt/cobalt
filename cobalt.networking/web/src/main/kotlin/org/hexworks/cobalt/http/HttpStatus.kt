package org.hexworks.cobalt.http

enum class HttpStatus private constructor(private val value: Short,
                                          val text: String) {

    // Information responses

    CONTINUE(100, "Continue"),
    SWITCHING_PROTOCOLS(101, "Switching Protocols"),
    PROCESSING(102, "Processing"),

    // Successful responses

    OK(200, "OK"),
    CREATED(201, "Created"),
    ACCEPTED(202, "Accepted"),
    NON_AUTHORITATIVE_INFORMATION(203, "Non-Authoritative Information"),
    NO_CONTENT(204, "No Content"),
    RESET_CONTENT(205, "Reset Content"),
    PARTIAL_CONTENT(206, "Partial Content"),
    MULTI_STATUS(207, "Multi-Status"),
    ALREADY_REPORTED(208, "Already Reported"),
    IM_USED(226, "IM Used"),

    // Redirection messages

    MULTIPLE_CHOICES(300, "Multiple Choices"),
    MOVED_PERMANENTLY(301, "Moved Permanently"),
    FOUND(302, "Found"),
    SEE_OTHER(303, "See Other"),
    NOT_MODIFIED(304, "Not Modified"),
    TEMPORARY_REDIRECT(307, "Temporary Redirect"),
    PERMANENT_REDIRECT(308, "Permanent Redirect"),

    // Client error responses

    BAD_REQUEST(400, "Bad Request"),
    UNAUTHORIZED(401, "Unauthorized"),
    PAYMENT_REQUIRED(402, "Payment Required"),
    FORBIDDEN(403, "Forbidden"),
    NOT_FOUND(404, "Not Found"),
    METHOD_NOT_ALLOWED(405, "Method Not Allowed"),
    NOT_ACCEPTABLE(406, "Not Acceptable"),
    PROXY_AUTHENTICATION_REQUIRED(407, "Proxy Authentication Required"),
    REQUEST_TIMEOUT(408, "Request Timeout"),
    CONFLICT(409, "Conflict"),
    GONE(410, "Gone"),
    LENGTH_REQUIRED(411, "Length Required"),
    PRECONDITION_FAILED(412, "Precondition Failed"),
    PAYLOAD_TOO_LARGE(413, "Payload Too Large"),
    URI_TOO_LONG(414, "URI Too Long"),
    UNSUPPORTED_MEDIA_TYPE(415, "Unsupported Media Type"),
    REQUESTED_RANGE_NOT_SATISFIABLE(416, "Requested range not satisfiable"),
    EXPECTATION_FAILED(417, "Expectation Failed"),
    I_AM_A_TEAPOT(418, "I'm a teapot"),
    MISDIRECTED_REQUEST(421, "Destination Locked"),
    UNPROCESSABLE_ENTITY(422, "Unprocessable Entity"),
    LOCKED(423, "Locked"),
    FAILED_DEPENDENCY(424, "Failed Dependency"),
    UPGRADE_REQUIRED(426, "Upgrade Required"),
    PRECONDITION_REQUIRED(428, "Precondition Required"),
    TOO_MANY_REQUESTS(429, "Too Many Requests"),
    REQUEST_HEADER_FIELDS_TOO_LARGE(431, "Request Header Fields Too Large"),
    UNAVAILABLE_FOR_LEGAL_REASONS(451, "Unavailable For Legal Reasons"),

    // Server error responses

    INTERNAL_SERVER_ERROR(500, "Internal Server Error"),
    NOT_IMPLEMENTED(501, "Not Implemented"),
    BAD_GATEWAY(502, "Bad Gateway"),
    SERVICE_UNAVAILABLE(503, "Service Unavailable"),
    GATEWAY_TIMEOUT(504, "Gateway Timeout"),
    HTTP_VERSION_NOT_SUPPORTED(505, "HTTP Version not supported"),
    VARIANT_ALSO_NEGOTIATES(506, "Variant Also Negotiates"),
    INSUFFICIENT_STORAGE(507, "Insufficient Storage"),
    LOOP_DETECTED(508, "Loop Detected"),
    NOT_EXTENDED(510, "Not Extended"),
    NETWORK_AUTHENTICATION_REQUIRED(511, "Network Authentication Required");

    /**
     * Tells whether this status class is in the [HttpStatusClass.INFORMATIONAL]
     * HTTP status class.
     */
    fun isInformational() = HttpStatusClass.INFORMATIONAL == statusClass()

    /**
     * Tells whether this status class is in the [HttpStatusClass.SUCCESSFUL]
     * HTTP status class.
     */
    fun isSuccessful() = HttpStatusClass.SUCCESSFUL == statusClass()

    /**
     * Tells whether this status class is in the [HttpStatusClass.REDIRECTION]
     * HTTP status class.
     */
    fun isRedirection() = HttpStatusClass.REDIRECTION == statusClass()

    /**
     * Tells whether this status class is in the [HttpStatusClass.CLIENT_ERROR]
     * HTTP status class.
     */
    fun isClientError() = HttpStatusClass.CLIENT_ERROR == statusClass()

    /**
     * Tells whether this status class is in the [HttpStatusClass.SERVER_ERROR]
     * HTTP status class.
     */
    fun isServerError() = HttpStatusClass.SERVER_ERROR == statusClass()

    /**
     * Tells whether this status class is in the
     * [HttpStatusClass.CLIENT_ERROR] or [HttpStatusClass.SERVER_ERROR] HTTP status class.
     */
    fun isError() = isClientError() || isServerError()


    /**
     * Returns the HTTP status statusClass of this status code.
     */
    fun statusClass(): HttpStatusClass {
        return HttpStatusClass.findByStatusCode(this)
    }

    override fun toString() = "$value $text"


    /**
     * Enumeration of HTTP status classes.
     */
    enum class HttpStatusClass private constructor(val value: Int) {

        INFORMATIONAL(1),
        SUCCESSFUL(2),
        REDIRECTION(3),
        CLIENT_ERROR(4),
        SERVER_ERROR(5);

        companion object {

            fun findByStatusCode(statusCode: Short): HttpStatusClass {
                return values().firstOrNull {
                    it.value == statusCode / 100
                } ?: throw IllegalArgumentException("No matching constant for '$statusCode'.")
            }

            fun findByStatusCode(status: HttpStatus): HttpStatusClass {
                return findByStatusCode(status.value)
            }
        }
    }

    companion object {

        /**
         * Finds a [HttpStatus] for the given `statusCode` or throws an exception
         * if no status is found.
         */
        fun findByStatusCode(statusCode: Short): HttpStatus {
            return values().firstOrNull {
                it.value == statusCode
            } ?: throw IllegalArgumentException("Can't find status code for '$statusCode'.")
        }
    }

}
