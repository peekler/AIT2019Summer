package hu.ait.tictactoe.model

object TicTacToeModel {

    public val EMPTY : Short = 0
    public val CIRCLE : Short = 1
    public val CROSS : Short = 2

    private var model = arrayOf(
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY),
        shortArrayOf(EMPTY, EMPTY, EMPTY)
    )

    public var nextPlayer = CIRCLE

    public fun resetModel() {
        for (i in 0..2) {
            for (j in 0..2) {
                model[i][j] = EMPTY
            }
        }
    }

    fun setFieldContent(x: Int, y: Int, player: Short) {
        model[x][y]=player
    }

    fun getFieldContent(x: Int, y: Int) = model[x][y]

    fun changeNextPlayer() {
        nextPlayer = if (nextPlayer == CIRCLE) CROSS else CIRCLE
    }

}