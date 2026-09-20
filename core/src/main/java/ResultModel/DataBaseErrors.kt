package ResultModel

enum class DataBaseErrors : Error {
    DATA_NOT_FOUND,
    DISK_FULL,
    CONSTRAINT_VIOLATION,
    IO_ERROR,
    UNKNOWN


}