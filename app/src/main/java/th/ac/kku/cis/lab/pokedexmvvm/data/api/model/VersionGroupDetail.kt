package th.ac.kku.cis.lab.pokedexmvvm.data.api.model

data class VersionGroupDetail(
    val level_learned_at: Int,
    val move_learn_method: MoveLearnMethod,
    val version_group: VersionGroup
)