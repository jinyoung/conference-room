<template>
    <div>
        <v-list two-line v-if="list.length > 0">
            <v-list-item-group 
                    v-model="selected" 
                    color="indigo"
                    @change="select"
            >
                <v-list-item v-for="(item, idx) in list" :key="idx">
                    <template v-slot:default="{ active }">
                        <v-list-item-avatar color="grey darken-1">
                        </v-list-item-avatar>
                        
                        <v-list-item-content>
                            <v-list-item-title>
                            </v-list-item-title>
                            <v-list-item-subtitle>
                                ReserveId :  {{item.reserveId }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                ReserveStartTime :  {{item.reserveStartTime }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                ReserveEndTime :  {{item.reserveEndTime }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                ReserveName :  {{item.reserveName }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                RoomId :  {{item.roomId }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                HostId :  {{item.hostId }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                UserId1 :  {{item.userId1 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                UserId2 :  {{item.userId2 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                UserId3 :  {{item.userId3 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                UserId4 :  {{item.userId4 }}
                            </v-list-item-subtitle>
                            <v-list-item-subtitle>
                                UserId5 :  {{item.userId5 }}
                            </v-list-item-subtitle>
                        </v-list-item-content>

                        <v-list-item-action>
                            <v-checkbox :input-value="active" color="indigo"></v-checkbox>
                        </v-list-item-action>
                    </template>
                </v-list-item>
            </v-list-item-group>
        </v-list>
    </div>
</template>


<script>
    const axios = require('axios').default;

    export default {
        name: 'ConferencePicker',
        props: {
            value: [String, Object, Array, Number, Boolean],
        },
        data: () => ({
            list: [],
            selected: null,
        }),
        async created() {
            var me = this;
            var temp = await axios.get(axios.fixUrl('/conferences'))
            if(temp.data) {
                me.list = temp.data._embedded.conferences;
            }

            if(me.value && typeof me.value == "object" && Object.values(me.value)[0]) {
                var id = Object.values(me.value)[0];
                var tmpValue = await axios.get(axios.fixUrl('/conferences/' + id))
                if(tmpValue.data) {
                    var val = tmpValue.data
                    me.list.forEach(function(item, idx) {
                        if(item.name == val.name) {
                            me.selected = idx
                        }
                    })
                }
            }
        },
        methods: {
            select(val) {
                var obj = {}
                if(val != undefined) {
                    var arr = this.list[val]._links.self.href.split('/');
                    obj['reserveId'] = arr[4]; 
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    
                    this.$emit('selected', obj);
                }
            },
        },
    };
</script>

