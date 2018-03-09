package wallet.coin.mota.motacoinwallet

/**
 * Created by mota on 7/3/2018.
 */
object Urls {
    val URL_LOGIN: String = "/operator/wallets"
    val URL_MINE: String = "/miner/mine"
    val URL_BALANCE: String = "/operator/{address}/balance"
    val URL_TRANSFER: String = "/operator/wallets/{wallet}/transactions"
    val URL_CREATE_ADDRESS: String = "/operator/wallets/{wallet}/addresses"
}