package snowflake

import java.util.random.RandomGenerator

class Snowflake {

    companion object {
        private const val UNUSED_BITS = 1
        private const val EPOCH_BITS = 41
        private const val NODE_ID_BITS = 10
        private const val SEQUENCE_BITS = 12

        private const val maxNodeId = (1L shl NODE_ID_BITS) - 1
        private const val maxSequence = (1L shl SEQUENCE_BITS) - 1
    }

    // 0 ~ maxNodeId 사이의 노드 아이디를 랜덤으로 생성합니다.
    private val nodeId: Long = RandomGenerator.getDefault().nextLong(maxNodeId + 1)
    // 기준 시간 (UTC 2024-01-01T00:00:00Z)
    private val startTimeMillis: Long = 1704067200000L

    private var lastTimeMillis: Long = startTimeMillis
    private var sequence: Long = 0L

    @Synchronized
    fun nextId(): Long {
        var currentTimeMillis = System.currentTimeMillis()

        if (currentTimeMillis < lastTimeMillis) {
            throw IllegalStateException("Invalid Time")
        }

        if (currentTimeMillis == lastTimeMillis) {
            // 시퀀스 값을 증가시키고, maxSequence 범위 내로 유지합니다.
            sequence = (sequence + 1) and maxSequence
            // 시퀀스가 0으로 되었다면, 밀리초 단위 시간이 넘어갈 때까지 대기합니다.
            if (sequence == 0L) {
                currentTimeMillis = waitNextMillis(currentTimeMillis)
            }
        } else {
            sequence = 0L
        }

        lastTimeMillis = currentTimeMillis

        return ((currentTimeMillis - startTimeMillis) shl (NODE_ID_BITS + SEQUENCE_BITS)) or
                (nodeId shl SEQUENCE_BITS) or
                sequence
    }

    private fun waitNextMillis(currentTimestamp: Long): Long {
        var current = currentTimestamp
        while (current <= lastTimeMillis) {
            current = System.currentTimeMillis()
        }
        return current
    }
}
