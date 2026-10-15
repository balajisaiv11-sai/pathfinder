package com.example.ui.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.imePadding
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material.icons.automirrored.filled.Send
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Psychology
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.data.ChatMessage
import com.example.data.MessageSender
import com.example.data.StudentProfile
import com.example.ui.ScreenDestination
import com.example.ui.components.EditorialBadge
import com.example.ui.theme.BorderNeutral
import com.example.ui.theme.EditorialSansFont
import com.example.ui.theme.EditorialSerifFont
import com.example.ui.theme.GoldMuted
import com.example.ui.theme.GoldMutedLight
import com.example.ui.theme.InkForestDeep
import com.example.ui.theme.InkForestMuted
import com.example.ui.theme.InkForestPrimary
import com.example.ui.theme.IvoryBackground
import com.example.ui.theme.PaperWhite
import com.example.ui.theme.SageSurface
import com.example.ui.theme.SageSurfaceVariant
import com.example.ui.theme.TerracottaAccent
import com.example.ui.theme.TerracottaContainer
import kotlinx.coroutines.launch

@Composable
fun DiscoverScreen(
  chatMessages: List<ChatMessage>,
  profile: StudentProfile,
  onSendMessage: (String) -> Unit,
  onRemoveInterest: (String) -> Unit,
  onAddInterest: (String) -> Unit,
  onNavigate: (ScreenDestination) -> Unit,
  modifier: Modifier = Modifier
) {
  BoxWithConstraints(modifier = modifier.fillMaxSize().background(IvoryBackground)) {
    val isWideScreen = maxWidth > 650.dp

    if (isWideScreen) {
      Row(modifier = Modifier.fillMaxSize()) {
        // Main conversation
        Box(modifier = Modifier.weight(1.3f)) {
          ConversationSection(
            chatMessages = chatMessages,
            onSendMessage = onSendMessage,
            modifier = Modifier.fillMaxSize()
          )
        }
        // Right Summary Panel
        Surface(
          modifier = Modifier
            .weight(0.9f)
            .fillMaxHeight(),
          color = SageSurface.copy(alpha = 0.4f),
          border = BorderStroke(1.dp, BorderNeutral)
        ) {
          LearnedProfileSummary(
            profile = profile,
            onRemoveInterest = onRemoveInterest,
            onAddInterest = onAddInterest,
            onNavigate = onNavigate,
            modifier = Modifier.padding(20.dp)
          )
        }
      }
    } else {
      // Mobile stacked layout
      Column(modifier = Modifier.fillMaxSize()) {
        Box(modifier = Modifier.weight(1f)) {
          ConversationSection(
            chatMessages = chatMessages,
            onSendMessage = onSendMessage,
            modifier = Modifier.fillMaxSize()
          )
        }

        Surface(
          modifier = Modifier.fillMaxWidth(),
          color = SageSurface.copy(alpha = 0.7f),
          border = BorderStroke(1.dp, BorderNeutral),
          shape = RoundedCornerShape(topStart = 16.dp, topEnd = 16.dp)
        ) {
          LearnedProfileSummary(
            profile = profile,
            onRemoveInterest = onRemoveInterest,
            onAddInterest = onAddInterest,
            onNavigate = onNavigate,
            modifier = Modifier.padding(16.dp),
            isCompact = true
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun ConversationSection(
  chatMessages: List<ChatMessage>,
  onSendMessage: (String) -> Unit,
  modifier: Modifier = Modifier
) {
  val listState = rememberLazyListState()
  var inputDraft by remember { mutableStateOf("") }
  val coroutineScope = rememberCoroutineScope()

  LaunchedEffect(chatMessages.size) {
    if (chatMessages.isNotEmpty()) {
      listState.animateScrollToItem(chatMessages.size - 1)
    }
  }

  Column(modifier = modifier.imePadding()) {
    // Top banner explaining guided exploration
    Surface(
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(horizontal = 16.dp, vertical = 12.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(10.dp)
      ) {
        Box(
          modifier = Modifier
            .size(32.dp)
            .clip(CircleShape)
            .background(SageSurface),
          contentAlignment = Alignment.Center
        ) {
          Icon(
            imageVector = Icons.Default.Psychology,
            contentDescription = null,
            tint = InkForestPrimary,
            modifier = Modifier.size(18.dp)
          )
        }
        Column {
          Text(
            text = "Guided Conversation",
            style = MaterialTheme.typography.titleSmall.copy(
              fontFamily = EditorialSerifFont,
              fontWeight = FontWeight.Bold,
              color = InkForestDeep
            )
          )
          Text(
            text = "Answer at your own pace. You can tap suggestions or type in your own words.",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontSize = 11.sp)
          )
        }
      }
    }

    // Message list
    LazyColumn(
      state = listState,
      modifier = Modifier
        .weight(1f)
        .padding(horizontal = 16.dp),
      verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
      item { Spacer(modifier = Modifier.height(12.dp)) }

      items(chatMessages, key = { it.id }) { msg ->
        if (msg.sender == MessageSender.GUIDE) {
          GuideBubble(msg = msg, onChipClick = onSendMessage)
        } else {
          UserBubble(msg = msg)
        }
      }

      item { Spacer(modifier = Modifier.height(16.dp)) }
    }

    // Input bar
    Surface(
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier
          .padding(horizontal = 12.dp, vertical = 8.dp)
          .fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
      ) {
        OutlinedTextField(
          value = inputDraft,
          onValueChange = { inputDraft = it },
          placeholder = {
            Text(
              "Share what you enjoy or ask a question...",
              style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted)
            )
          },
          shape = RoundedCornerShape(20.dp),
          colors = OutlinedTextFieldDefaults.colors(
            focusedBorderColor = TerracottaAccent,
            unfocusedBorderColor = BorderNeutral,
            focusedContainerColor = IvoryBackground,
            unfocusedContainerColor = IvoryBackground
          ),
          modifier = Modifier
            .weight(1f)
            .testTag("discover_chat_input"),
          maxLines = 3
        )

        Button(
          onClick = {
            if (inputDraft.isNotBlank()) {
              onSendMessage(inputDraft.trim())
              inputDraft = ""
            }
          },
          enabled = inputDraft.isNotBlank(),
          shape = CircleShape,
          colors = ButtonDefaults.buttonColors(
            containerColor = TerracottaAccent,
            disabledContainerColor = BorderNeutral
          ),
          modifier = Modifier
            .size(46.dp)
            .testTag("discover_chat_send_button")
        ) {
          Icon(
            imageVector = Icons.AutoMirrored.Filled.Send,
            contentDescription = "Send",
            tint = PaperWhite,
            modifier = Modifier.size(18.dp)
          )
        }
      }
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun GuideBubble(
  msg: ChatMessage,
  onChipClick: (String) -> Unit
) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.Start
  ) {
    Surface(
      shape = RoundedCornerShape(topStart = 4.dp, topEnd = 16.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
      color = PaperWhite,
      border = BorderStroke(1.dp, BorderNeutral),
      shadowElevation = 1.dp,
      modifier = Modifier.fillMaxWidth(0.92f)
    ) {
      Column(modifier = Modifier.padding(14.dp)) {
        Text(
          text = msg.text,
          style = MaterialTheme.typography.bodyMedium.copy(
            fontFamily = EditorialSansFont,
            color = InkForestDeep,
            lineHeight = 22.sp
          )
        )

        if (msg.suggestedChips.isNotEmpty()) {
          Spacer(modifier = Modifier.height(12.dp))
          Text(
            text = "Tap an option or type below:",
            style = MaterialTheme.typography.labelSmall.copy(
              color = InkForestMuted,
              fontWeight = FontWeight.Medium
            )
          )
          Spacer(modifier = Modifier.height(6.dp))

          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            msg.suggestedChips.forEach { chipText ->
              Surface(
                shape = RoundedCornerShape(12.dp),
                color = if (chipText.contains("directions", ignoreCase = true)) TerracottaContainer else SageSurface,
                border = BorderStroke(
                  1.dp,
                  if (chipText.contains("directions", ignoreCase = true)) TerracottaAccent else BorderNeutral
                ),
                modifier = Modifier
                  .clip(RoundedCornerShape(12.dp))
                  .clickable { onChipClick(chipText) }
                  .testTag("discover_chip_$chipText")
              ) {
                Text(
                  text = chipText,
                  style = MaterialTheme.typography.labelSmall.copy(
                    fontFamily = EditorialSansFont,
                    fontWeight = FontWeight.SemiBold,
                    color = if (chipText.contains("directions", ignoreCase = true)) TerracottaAccent else InkForestPrimary
                  ),
                  modifier = Modifier.padding(horizontal = 12.dp, vertical = 7.dp)
                )
              }
            }
          }
        }
      }
    }
  }
}

@Composable
private fun UserBubble(msg: ChatMessage) {
  Column(
    modifier = Modifier.fillMaxWidth(),
    horizontalAlignment = Alignment.End
  ) {
    Surface(
      shape = RoundedCornerShape(topStart = 16.dp, topEnd = 4.dp, bottomStart = 16.dp, bottomEnd = 16.dp),
      color = TerracottaAccent,
      modifier = Modifier.fillMaxWidth(0.85f)
    ) {
      Text(
        text = msg.text,
        style = MaterialTheme.typography.bodyMedium.copy(
          fontFamily = EditorialSansFont,
          color = PaperWhite,
          lineHeight = 21.sp
        ),
        modifier = Modifier.padding(14.dp)
      )
    }
  }
}

@OptIn(ExperimentalLayoutApi::class)
@Composable
private fun LearnedProfileSummary(
  profile: StudentProfile,
  onRemoveInterest: (String) -> Unit,
  onAddInterest: (String) -> Unit,
  onNavigate: (ScreenDestination) -> Unit,
  modifier: Modifier = Modifier,
  isCompact: Boolean = false
) {
  var isExpandedInCompact by remember { mutableStateOf(false) }

  Column(
    modifier = modifier,
    verticalArrangement = Arrangement.spacedBy(12.dp)
  ) {
    Row(
      modifier = Modifier.fillMaxWidth(),
      horizontalArrangement = Arrangement.SpaceBetween,
      verticalAlignment = Alignment.CenterVertically
    ) {
      Column {
        Text(
          text = "What I've Learned",
          style = MaterialTheme.typography.titleMedium.copy(
            fontFamily = EditorialSerifFont,
            fontWeight = FontWeight.Bold,
            color = InkForestDeep
          )
        )
        Text(
          text = "Your evolving profile summary",
          style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontSize = 11.sp)
        )
      }

      if (isCompact) {
        TextButton(onClick = { isExpandedInCompact = !isExpandedInCompact }) {
          Text(
            text = if (isExpandedInCompact) "Hide" else "Show Details",
            style = MaterialTheme.typography.labelSmall.copy(color = TerracottaAccent)
          )
        }
      }
    }

    if (!isCompact || isExpandedInCompact) {
      // Stage Chip
      Row(
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Text("Stage:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
        EditorialBadge(
          text = profile.studyStage.displayName,
          backgroundColor = TerracottaContainer,
          textColor = TerracottaAccent
        )
      }

      // Interests Chips
      Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
        Text("Interests & Curiosity:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
        if (profile.interests.isEmpty()) {
          Text(
            text = "No interests noted yet. Tap chips in conversation to add.",
            style = MaterialTheme.typography.bodySmall.copy(color = InkForestMuted, fontSize = 11.sp)
          )
        } else {
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            profile.interests.forEach { interest ->
              Surface(
                shape = RoundedCornerShape(8.dp),
                color = PaperWhite,
                border = BorderStroke(1.dp, BorderNeutral)
              ) {
                Row(
                  modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
                  verticalAlignment = Alignment.CenterVertically,
                  horizontalArrangement = Arrangement.spacedBy(4.dp)
                ) {
                  Text(
                    text = interest,
                    style = MaterialTheme.typography.labelSmall.copy(color = InkForestDeep)
                  )
                  Icon(
                    imageVector = Icons.Default.Close,
                    contentDescription = "Remove interest",
                    tint = InkForestMuted,
                    modifier = Modifier
                      .size(12.dp)
                      .clickable { onRemoveInterest(interest) }
                  )
                }
              }
            }
          }
        }
      }

      // Strengths
      if (profile.strengths.isNotEmpty()) {
        Column(verticalArrangement = Arrangement.spacedBy(4.dp)) {
          Text("Strengths mentioned:", style = MaterialTheme.typography.labelSmall.copy(color = InkForestMuted))
          FlowRow(
            horizontalArrangement = Arrangement.spacedBy(6.dp),
            verticalArrangement = Arrangement.spacedBy(6.dp)
          ) {
            profile.strengths.forEach { str ->
              EditorialBadge(text = str, backgroundColor = SageSurface, textColor = InkForestDeep)
            }
          }
        }
      }
    }

    // Action button to view directions
    Button(
      onClick = { onNavigate(ScreenDestination.CAREERS) },
      colors = ButtonDefaults.buttonColors(containerColor = TerracottaAccent),
      shape = RoundedCornerShape(10.dp),
      modifier = Modifier
        .fillMaxWidth()
        .height(44.dp)
        .testTag("discover_show_directions_button")
    ) {
      Text("Show me directions", color = PaperWhite, fontWeight = FontWeight.SemiBold)
      Spacer(modifier = Modifier.width(6.dp))
      Icon(
        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
        contentDescription = null,
        tint = PaperWhite,
        modifier = Modifier.size(16.dp)
      )
    }

    // How suggestions work disclosure
    Surface(
      shape = RoundedCornerShape(8.dp),
      color = GoldMutedLight,
      border = BorderStroke(1.dp, GoldMuted.copy(alpha = 0.25f)),
      modifier = Modifier.fillMaxWidth()
    ) {
      Row(
        modifier = Modifier.padding(10.dp),
        verticalAlignment = Alignment.Top,
        horizontalArrangement = Arrangement.spacedBy(6.dp)
      ) {
        Icon(
          imageVector = Icons.Default.Info,
          contentDescription = null,
          tint = GoldMuted,
          modifier = Modifier.size(14.dp)
        )
        Text(
          text = "How suggestions work: We match your stated interests and stage to sample course & career tags. This is not an aptitude score, prediction, or diagnostic test.",
          style = MaterialTheme.typography.bodySmall.copy(
            fontFamily = EditorialSansFont,
            color = InkForestDeep,
            fontSize = 11.sp,
            lineHeight = 15.sp
          )
        )
      }
    }
  }
}
