package com.wcsm.androidlearninghub

import android.content.Intent
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.wcsm.androidlearninghub.guide_activity.ActivityActivity
import com.wcsm.androidlearninghub.guide_api.ApiActivity
import com.wcsm.androidlearninghub.guide_coroutines.CoroutinesActivity
import com.wcsm.androidlearninghub.guide_fragment.FragmentActivity
import com.wcsm.androidlearninghub.guide_interface_components.InterfaceComponentsActivity
import com.wcsm.androidlearninghub.guide_notification.NotificationActivity
import com.wcsm.androidlearninghub.guide_recyclerview.RecyclerviewActivity
import com.wcsm.androidlearninghub.guide_scrollview.ScrollviewActivity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        
        setContent {
            LearningHub()
        }

    }

    @Preview(showBackground = true)
    @Composable
    private fun LearningHub() {
        val contents = listOf(
            ContentItem(
                title = "Scrollview",
                activity = ScrollviewActivity()
            ),
            ContentItem(
                title = "Activity",
                activity = ActivityActivity()
            ),
            ContentItem(
                title = "Fragment",
                activity = FragmentActivity()
            ),
            ContentItem(
                title = "RecyclerView",
                activity = RecyclerviewActivity()
            ),
            ContentItem(
                title = "Interface Components",
                activity = InterfaceComponentsActivity()
            ),
            ContentItem(
                title = "Coroutines",
                activity = CoroutinesActivity()
            ),
            ContentItem(
                title = "Api",
                activity = ApiActivity()
            ),
            ContentItem(
                title = "Notificação",
                activity = NotificationActivity()
            )
        )

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Android Leaning Hub",
                fontSize = 32.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(8.dp)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "CONTEÚDOS",
                fontSize = 24.sp,
                fontWeight = FontWeight.SemiBold
            )

            HorizontalDivider(
                color = Color.Black,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 12.dp)
            )

            LazyColumn {
                items(contents) {
                    ContentItemCard(
                        title = it.title,
                        it.activity
                    )
                }
            }
        }
    }

    @Composable
    private fun ContentItemCard(title: String, activity: AppCompatActivity) {
        val intent = Intent(this, activity::class.java)

        Card(
            modifier = Modifier
                .width(300.dp)
                .padding(8.dp)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(4.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = title,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold
                )
                Button(
                    onClick = {
                        startActivity(intent)
                    }
                ) {
                    Text(
                        text = "Ver Conteúdo",
                        fontWeight = FontWeight.SemiBold
                    )
                    Spacer(modifier = Modifier.width(4.dp))
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "Go to see this content"
                    )
                }
            }
        }
    }

}